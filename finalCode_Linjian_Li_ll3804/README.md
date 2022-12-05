# Linjian Li
# README

## Directories

In this folder, we have the following folders with the following contents:

- ana_code: Data analysis folder, in which all data analysis codes are named according to the task name.
- etl_code: Data cleaning code. This dropped the lines with incorrect columns.
- profiling_code: Data profiling code. Before running data cleaning, this can get a general understanding of the dataset.
- screenshots: Screenshot of all operations.
- csv: All the inputs and outputs of the analysis in csv format, named according to the tasks. 
- *** data_ingest: Data was downloaded on https://data.cityofnewyork.us/Public-Safety/NYPD-Shooting-Incident-Data-Year-To-Date-/5ucz-vwe8 and it was a csv file. The data was already ready for profiling and cleaning. 

## Compile and Run

- All codes are run under the Mac environment. All codes are compiled and run following the instructions under brightspace, Fall 2022 Processing Big Data for Analytics Applications, Content, How to run a MapReduce job on Peel. Nothing fancy was added or used. 


## Detailed explanation

- input: All input files can be found under user/ll3804/hw/input.
- output: Please see the detailed explanation below to find where to see the different output results. 

- profiling_code: user/ll3804/hw/input/NYPD_Shooting.csv was first run by the profiling code. The result of the first run can be found at user/ll3804/output64. Then user/ll3804/hw/input/NYPD_shootingCleaned.csv was run by the profiling code to get an general idea of the cleaned dataset. The result of the second run can be found at user/ll3804/output63.

- etl_code: user/ll3804/hw/input/NYPD_Shooting.csv was run by the cleaning code. Incorrect columns were dropped and the output can be found at user/ll3804/output61. This output was converted into NYPD_shootingCleaned.csv and ran by all subsequent analysis codes. 

- ana_code/boro: The purpose of this code is to understand how many shootings occurred in NYC based on different boroughs. user/ll3804/hw/input/NYPD_shootingCleaned.csv was used as the input and the output can be found at user/ll3804/output65. 

- ana_code/coordinates: The aim of this code is to pick out the coordinate information of each shooting, namely, latitude and longitude, for further visualization purposes. user/ll3804/hw/input/NYPD_shootingCleaned.csv was used as the input and the output can be found at user/ll3804/output73. 

- ana_code/locatioon: The purpose of this code is to find out how many shootings occurred inside and how many occurred ouside. user/ll3804/hw/input/NYPD_shootingCleaned.csv was used as the input and the output can be found at user/ll3804/output66. 

- ana_code/Perp_Race: The purpose of this code is to understand the race distribution of the shooters. user/ll3804/hw/input/NYPD_shootingCleaned.csv was used as the input and the output can be found at user/ll3804/output67. 

- ana_code/Perp_Sex: The purpose of this code is to understand the sex distribution of the shooters. user/ll3804/hw/input/NYPD_shootingCleaned.csv was used as the input and the output can be found at user/ll3804/output68. 

- ana_code/Vic_Race: The purpose of this code is to understand the race distribution of the victims. user/ll3804/hw/input/NYPD_shootingCleaned.csv was used as the input and the output can be found at user/ll3804/output69. 

- ana_code/Vic_Sex: The purpose of this code is to understand the sex distribution of the victims. user/ll3804/hw/input/NYPD_shootingCleaned.csv was used as the input and the output can be found at user/ll3804/output70. 