# Robert ZHeng
# README

## Directories

In this folder, we have the following folders with the following contents:

- ana_code: Data analysis folder, Largly empty due to the intent to do analysis through Tableau rather than code.
- etl_code: Data cleaning code. This dropped the lines with incorrect columns.
- profiling_code: Data profiling code. Before running data cleaning, this was used to get a general understanding of the data
- screenshots: Screenshot of all operations.
- csv: All the inputs and outputs of the analysis in csv format, named according to the tasks. 
- *** data_ingest: Data was downloaded on https://data.cityofnewyork.us/Public-Safety/NYPD-Arrest-Data-Year-to-Date-/uip8-fykc and it was a csv file. 

## Compile and Run

- All codes are run under the Windows environment. All the code were compiled and run by following the instructions under the brightspace class: Fall 2022 Processing Big Data for Analytics Applications. Specifically: How to run a MapReduce job on Peel. 


## Detailed explanation

- input: All input files can be found in the csv folder

## Running the files

The main steps are:
Putting into Peel with scp
compiling with javac
Create jar file with compiled class files
Run jar file
More detailed explaination at this URL: https://brightspace.nyu.edu/content/enforced/222778-FA22_CSCI-UA_476_1_001/Compiling%20and%20running%20an%20application.pdf?_&d2lSessionVal=TeTiYtMnuhzBcvv4QOQde7lug 


- output: Please see the detailed explanation below 

## Getting the result

After running any MapReduce task on Peel, you check the results in HDFS folder part-r-00000

```bash
hdfs dfs -cat final/output/exampleDirectory/part-r-00000
```

