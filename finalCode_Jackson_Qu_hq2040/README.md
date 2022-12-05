# README

## Directories

In this folder, we have the following folders with the following contents:

- ana_code: Data analysis folder, in which all data analysis codes are named according to the task name.
- etl_code: Data cleaning code. We mainly cleaned the lines with incorrect columns.
- profiling_code: Data profiling code. Before running data cleaning, we can get a general understanding of the dataset by running the data profiling code.
- screenshots: Screenshot of all operations.
- test_code: Obsolete code, including old data cleaning code.

## Preparation

Before we compile and run the code, we need to make the following preparations.

1. Since we use the third-party Jar package, we need to reorganize the code into a folder to ensure that all the code can be compiled and run normally. We can create a new folder named `final` and move the subfolders under `ana_code`, `etl_code`, `profiling_code` to final.

The `final` folder structure should look like this:

```
final
├─Clean
├─CountADDRPCTCD
├─CountAGE
├─CountBORO
├─CountByHour
├─CountByMonth
├─CountKYCD
├─CountLAWCAT
├─CountLines
├─CountRACE
└─lib
```

2. Use the following command to upload the final folder to Peel:

```bash
scp -r final/ <NetID>@peel.hpc.nyu.edu:~/
```

3. Configure HDFS.

Connect to Peel, switch directory to final:

```bash
ssh <NetID>@peel.hpc.nyu.edu
cd final/
```

Create folders in HDFS and upload files to HDFS(Note that the data table needs to be downloaded from NY City OpenData):

Data download link: https://data.cityofnewyork.us/api/views/5uac-w243/rows.csv?accessType=DOWNLOAD

```bash
hdfs dfs -mkdir lib
hdfs dfs -mkdir final
hdfs dfs -mkdir final/data
hdfs dfs -mkdir final/lib
hdfs dfs -mkdir final/output

hdfs dfs -put lib/opencsv-5.7.1.jar lib/
hdfs dfs -put lib/opencsv-5.7.1.jar final/lib/
hdfs dfs -put NYPD_Complaint_Data_Current__Year_To_Date_.csv final/data
```

The HDFS folder structure should look like this:

```
<NetID>
├─final
	├─data
		├─NYPD_Complaint_Data_Current__Year_To_Date_.csv
	├─lib
		├─opencsv-5.7.1.jar
	├─output
└─lib
	├─opencsv-5.7.1.jar
```

4. Set environment variables.

Because we use third-party packages, we need to set up HADOOP_ CLASSPATH:

```bash
export HADOOP_CLASSPATH=/home/<NetID>/final/lib/opencsv-5.7.1.jar:$HADOOP_CLASSPATH
```

## Compile and run

In each separate sub folder, we have a running script named run.sh. If the previous configuration is correct, we can directly run the script to complete the cleaning and analysis tasks.

Here we take Countlines and Clean as examples to show how to compile and run Map Reduce tasks:

```bash
cd Countlines
bash run.sh
# Compiling, packaging and running...
# Get the result
hdfs dfs -cat final/output/Countlines/part-r-00000
```

```bash
cd Clean
bash run.sh
# Compiling, packaging and running...
# Get the result
hdfs dfs -tail final/output/Clean/part-r-00000
```

Before running other analysis tasks, we need to synthesize the data cleaning results into a new table and transfer it to HDFS.

```bash
# Merge to a new table
hadoop fs -getmerge final/output/Clean ./Clean_data.csv
# Upload
hdfs dfs -put ./Clean_data.csv final/data/
# Then run other analysis tasks
cd xxx
bash run.sh
```

## Get the result

After running all the analysis tasks, we use CountADDRPCTCD as an example to show how to obtain analysis results.

```bash
hdfs dfs -cat final/output/CountADDRPCTCD/part-r-00000
```
