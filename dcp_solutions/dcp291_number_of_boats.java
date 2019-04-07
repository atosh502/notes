package com.atosh502;

import java.util.*;
import java.lang.*;
import java.io.*;

// An imminent hurricane threatens the coastal town of Codeville. 
// If at most two people can fit in a rescue boat, 
// and the maximum weight limit for a given boat is k, 
// determine how many boats will be needed to save everyone.

// For example, given a population with weights [100, 200, 150, 80] 
// and a boat limit of 200, the smallest number of boats required will be three.

/* Name of the class has to be "Main" only if the class is public. */
class dcp291_number_of_boats
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] weights = {100, 200, 150, 80};
		int limit = 200;
		
		int population = weights.length;
		
		// sort the weights
		Arrays.sort(weights);
		
		int running_weight = 0;
		List<Integer> boats = new ArrayList<>();
		
		for (int weight : weights){
		    
		    if (running_weight + weight <= limit){
		        running_weight += weight;
		    } else {
		        boats.add(running_weight);
		        running_weight = weight;
		    }
		}
		
		// the last of the weights also requires a boat
		boats.add(running_weight);
		
		System.out.println(boats.toString());
		System.out.println(boats.size());
		
	}
}
