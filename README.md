# Shortest route finder

A program for generating the shortest route between two nodes.  
There are two algorithms on offer: Dijkstra and A*.

## How to run
`Run the main method in MapGraph` 

You can change the to and from nodes in the top of the Main().  
There are three data files provided:
*  edgesIceland.txt
*  nodesIceland.txt
*  placesOfInterestIceland.txt

The first two files are used to generate the edges and nodes. The lats file is a list of places of interest (such as cities) and their corresponding nodes.

## How to display the result
The program will output four files:
*  outputAStar.txt
*  outputAStar_nodesOnly.csv
*  outputDijkstra.txt
*  outputDijkstra_nodesOnly.csv

There are two sets of files, A* and Dijkstra. Each contains a .txt file and a .csv file.  
The .txt files contains the travel time, the distance, the number of nodes traversed, and a list of coordinates for all the traversed nodes with a latitude/longitude header. 
The .csv files only contains the list of coordinates and the latitude/longitude header.

**The program does unfortunately not contain a visualizer yet, but you can find a visualizer tool [here (GPS Visualizer)](https://www.gpsvisualizer.com/map_input?form=data).**

#### How to use GPS Visualizer:

You can copy the list of coordinates from either the .txt or .csv files and paste it into the data field on the GPs Visualizer.

OR 

You can upload one of the .csv files directly to GPS Visualizer