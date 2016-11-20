package com2031.org.InvCount;

public class InversionCount {

	// running total of inversions counted in the given list.
	private static int total; 

	public static void main(String[] args) {
		// list being used to check number of inversions. 
		int[] list = {1,2,3,5,4,6}; 	
		// runs the recursive method to count the inversions using predefined list as parameter.
		countInversions(list); 					
		// print number of inversion when algorithm is complete. 
		System.out.println("Inversions: " + total); 
	}

		/* 		CountInversions creates an integer storing the length of the list.
		 * 		if the list consists of one element, simple return the list. (there are 0 inversions)
		 * 		returns the list to the next operation of countInversions (if there is one, otherwise return the result to main.)
		 * 		if the list is nonempty, divide the list into two halves. 
		 */
		
	 static public int[] countInversions(int[] list) { 
		int n = list.length; 	
		if (n == 1) { 			
			return list; 		
		} else { 			
			
			int[] A = new int[(list.length)/2]; // A contains the first half of the elements. 
			int[] B = new int[(list.length)/2]; // B contains the second half of the elements.

		    System.arraycopy(list, 0, A, 0, A.length);		
		    System.arraycopy(list, A.length, B, 0, B.length);
		    
			/*		run the recursive countInversions method against A and redefine the list A respectively.
			 * 		run the recursive countInversions method against B and redefine the list B respectively..
			 *		run mergeAndCount to sort and count the number of inversions in the given list. 
			 *		Once finished, return the list, possibly to the recursive routine with the new sorted list. 
			 */
		    
			A = countInversions(A); 	
			B = countInversions(B); 	
			mergeAndCount(A, B, list); 	 
			return list; 				 
		}
	}
	
	private static void mergeAndCount(int[] A, int[] B, int[] L) { 
		
		// initialise pointers for list A, B, and L respectively. Initialised to point to the front elements of each list. 
		int i = 0, j = 0, k = 0; 
		
		/*		while current pointers are not at the end of either list, compare the two elements currently being pointed to in each list. 
		 * 		append the smaller of these two elements, in this case, A[i], to the new sorted, L, increment left-side (A) list pointer, i by one.
		 * 		append the smaller of these two elements, in this case, B[j], to the sorted list, L.
		 * 		increment right-side (B) list pointer, j by one, then add the number of the (unsorted) remaining elements of the list to the running total. 
		 */
		
		while (i < A.length && j < B.length) { 	
			if (A[i] < B[j]) { 				
				L[k] = A[i]; 					 
				i++; 							
			} else {							// if B[j] > A[i]
				L[k] = B[j]; 					
				j++; 							
				total += (A.length - i); 	
			}
			
			k++; 								// increment the pointer to the list L by one. 
		}
			
			/*    merge any of the remaining elements into the list L if necessary. 
			 *    append the remaining elements of A to list L. 
			 *    while the pointer to A is not at the end of the list, 
		     *    increment i and k until the remaining elements have been dealt with and while loop ends.*/
		
			while (i < A.length) { 		
				L[k++] = A[i++];
			}
			
			/* while the pointer to B is not at the end of the list,
			 * append the remaining elements of B to list L. 
			 * increment j and k until the remaining elements have been dealt with and while loop ends.
			 */
			
			while (j < B.length) { 		
				L[k++] = B[j++];
			}
	}
	
}
