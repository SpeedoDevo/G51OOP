/*
 * array manipulation exercise
 * @author: Barnabas Forgo (bxf03u, 4211949)
 */

public class Union {
	
	final static int ARRAY_SIZE = 100;
	
	public static void main(String[] args) {

		int[] arrayFoo = new int[ARRAY_SIZE];
		int[] arrayBar = new int[ARRAY_SIZE];
		int[] intersection = new int[ARRAY_SIZE];
		int[] union = new int[2*ARRAY_SIZE];
		
		fillArray(arrayFoo, "Enter data for array 1 (0 to finish): ");
		fillArray(arrayBar, "Enter data for array 2 (0 to finish): ");
		System.out.println();
		
		displayElements(arrayFoo, "Raw Data for first array is: ");
		mergeSort(arrayFoo);
		displayElements(arrayFoo, "Sorted Data for first array is: ");
		
		displayElements(arrayBar, "Raw Data for second array is: ");
		mergeSort(arrayBar);
		displayElements(arrayBar, "Sorted Data for second array is: ");
		
		intersection = intersection(arrayFoo, arrayBar);
		displayElements(intersection, "Common data (intersection) is: ");
		System.out.println("Count of common data is: " + trueLength(intersection));
		
		union = union(arrayFoo, arrayBar);
		displayElements(union, "Total unique data (union) is: ");
		
		displayElements(minus(arrayFoo, intersection), "Non-common data for first array is: ");
		displayElements(minus(arrayBar, intersection), "Non-common data for second array is: ");
	}
	
	//method for filling arrays
	static void fillArray(int[] array, String promptString) {
		int input;
		for(int i = 0; i < ARRAY_SIZE; i++) {
			System.out.print(promptString);
			input = G51OOPInput.readInt();
			if(input == 0) {
				//break the filling loop if 0 is entered  
				i = ARRAY_SIZE;
			} else {
				//only add a value to the array if it doesn't already exist in it
				if (checkRepetition(input, array)) {
					i--; //decrement the counter so that there are no 0s between actual values
				} else {
					array[i] = input;
				}
			}
		}
	}
	
	//method for generating the intersection of two arrays
	//that don't contain duplicated values
	static int[] intersection(int[] left, int[] right) {
		int[] intersection = new int[ARRAY_SIZE];
		int i = 0;
		//two nested "for" loops for checking every combination of the arrays
		for(int iLeft = 0; iLeft < trueLength(left); iLeft++) {
			for(int iRight = 0; iRight < trueLength(right); iRight++) {
				if (left[iLeft] == right[iRight]) {
					intersection[i++] = left[iLeft];
				}
			}
		}
		return intersection;
	}
	
	//method for generating the union of two arrays
	//that don't contain duplicated values
	static int[] union(int[] left, int[] right) {
		int leftLength = trueLength(left);
		int rightLength = trueLength(right);
		int[] union = new int[leftLength + rightLength];
		int repetitionCounter = 0;
		
		//loop that goes through on both arrays
		for(int i = 0; i < leftLength + rightLength; i++) {
			if(i < leftLength) {
				//first add every element of the array on the left
				union[i] = left[i];
			} else {
				//checking for repetition before adding the value to the array
				if (!checkRepetition(right[i-leftLength], union)) {
					//union[i-repetitionCounter] this indexing makes sure that no 0s get between values
					union[i-repetitionCounter] = right[i-leftLength];
				} else {
					repetitionCounter++;
				}
			}
		}
		//resorting for the union array gets unsorted in the process
		mergeSort(union);
		return union;
	}
	
	//method that deletes values from array A if it exists in array B therefore the name minus
	//in this exercise it's only used for getting the non-common data from the two arrays
	//non-common = A \ (A `intersection´ B) therefore the naming of the second array intersection
	static int[] minus(int[] array, int[] intersection) {
		int arrayLength = trueLength(array);
		int j = 0;
		int[] minus = new int[arrayLength];
		for(int i = 0; i < arrayLength; i++) {
			//if the candidate value in array doesn't exist in intersection then add it to the minus[] to be returned
			if (!checkRepetition(array[i], intersection)) {
				minus[j++] = array[i];
			}
		}
		return minus;
	}
	
	//method that returns a true if the value exists in the array otherwise a false
	static Boolean checkRepetition(int candidate, int[] array) {
		Boolean repeat = false;
		for(int i = 0; i < trueLength(array); i++) {
			if (candidate == array[i]) {
				repeat = true;
			}
		}
		return repeat;
	}
	
	//method that returns the number of items in an array rather than the declared size
	//used the assumption that no values come after 0s
	static int trueLength(int[] array) {
		int i = 0;
		if (array.length == 0 || array[0] == 0) {
			//return 0 if the declared length is 0 or the first element is 0;
			i = 0;
		} else if (array[array.length-1] == 0) {
			//if the last element is not a value but a 0
			//	count the number of items until the first 0
			while(array[i] != 0) {
				i++;
			}
		} else {
			//if the leading nor the trailing value was zero then return .length 
			i = array.length;
		}
		return i;
	}
	
	//method for printing every element of an array with a nice message before it
	static void displayElements(int[] array, String string) {
		System.out.print(string);
		if (trueLength(array) == 0) {
			//checking for empty array
			System.out.print("Array is empty.");
		} else {
			//else print until the last element
			for(int i = 0; i < trueLength(array); i++) {
				System.out.print(array[i] + " ");
			}
		}
		System.out.println();
	}

	//I implemented mergeSort
	//used http://goo.gl/W6fb3s as a template /*well fully working java template*/
	//but tailored it for this particular exercise
	static void mergeSort(int[] array) {
		if (trueLength(array) > 1) {
			int[] left = halve(array, true);
			int[] right = halve(array, false);
			
			mergeSort(left);
			mergeSort(right);
			
			merge(array, left, right);
		}
	}
	
	static int[] halve(int[] array, Boolean isLeft) {
		int sizeLeft = trueLength(array) / 2;
		int sizeRight = trueLength(array) - sizeLeft;
		if(isLeft){
			int[] left = new int[sizeLeft];
			for(int i = 0; i < sizeLeft; i++) {
				left[i] = array[i];
			}
			return left;
		} else {
			int[] right = new int[sizeRight];
			for(int i = 0; i < sizeRight; i++) {
				right[i] = array[i + sizeLeft];
			}
			return right;
		}
	}
	
	
	static void merge(int[] result, int[] left, int[] right) {
		int iLeft = 0;
		int iRight = 0;
		
		for (int i = 0; i < trueLength(left) + trueLength(right); i++) {
			if (iRight >= right.length || (iLeft < left.length && left[iLeft] <= right[iRight])) {
				result[i] = left[iLeft++];
			} else {
				result[i] = right[iRight++];
			}
		}
	}	

}
