import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Task_4 {

	static class ThreadAverage extends Thread {

		private Integer[] arr;
		private int len;
		private float averageValue;
		
		public ThreadAverage(Integer[] userNumsToArray) {
	        this.arr = userNumsToArray;
	    }
		
		public void run() {
			len = arr.length;
			float sum = 0;
			
			for (int i = 0; i < len; i++) {
	            sum += arr[i];
	        }
			
			averageValue = sum / len;

		}
		public String show() {
			return String.format("%.02f", averageValue);
		}
	}
		
	static class ThreadMinimum extends Thread {
		
		private Integer[] arr;
		private int len;
		private int minValue;
		
		public ThreadMinimum(Integer[] userNumsToArray) {
	        this.arr = userNumsToArray;
	    }
		
		public void run() {
			len = arr.length;
			minValue = arr[0];
			
			for (int i = 0; i < len; i++) {
				if (arr[i] < minValue) {
					minValue = arr[i];
				}
			}
		}
		public int show() {
			return minValue;
		}
	}
		
	static class ThreadMaximum extends Thread {
		
		private Integer[] arr;
		private int len;
		private int maxValue;
		
		public ThreadMaximum(Integer[] userNumsToArray) {
	        this.arr = userNumsToArray;
	    }
		
		public void run() {
			len = arr.length;
			maxValue = arr[0];
			
			for (int i = 0; i < len; i++) {
				if (arr[i] > maxValue) {
					maxValue = arr[i];
				}
			}
		}
		public int show() {
			return maxValue;
		}
	}
			
	static class ThreadMedian extends Thread {
		
		private Integer[] arr;
		private int len;
		private float medianValue;
		
		public ThreadMedian(Integer[] userNumsToArray) {
	        this.arr = userNumsToArray;
	    }
		
		public void run() {
			len = arr.length;
			Integer[] arraySorted = arr;
			Arrays.sort(arraySorted);
			
			int mid = len / 2;
			
			if (len % 2 == 0) {
				medianValue = (arraySorted[mid]+arraySorted[mid-1])/2;
			}
			else {
				medianValue = arraySorted[mid];
			}
		}
		public float show() {
			return medianValue;
		}
	}
	
	static class ThreadStandardDeviation extends Thread {
		
		private Integer[] arr;
		private int len;
		private double standardDeviationValue;
		
		public ThreadStandardDeviation(Integer[] userNumsToArray) {
	        this.arr = userNumsToArray;
	    }
		
		public void run() {
			len = arr.length;
			float sum = 0;
			float avg = 0;

	        for(int num : arr) {
	            sum += num;
	        }

	        avg = sum/len;

	        for(int num : arr) {
	            standardDeviationValue += Math.pow(num - avg, 2);
	        }
	        standardDeviationValue = Math.sqrt(standardDeviationValue/len);
		}
		public String show() {
			return String.format("%.02f", standardDeviationValue);
		}
	}
	
	public static void main(String[] args) {
		// initialising array list
		ArrayList<Integer> userNums = new ArrayList<Integer>();
		
		// taking user input
		System.out.println("Please enter the numbers you want to perform the operations on - separated by pressing Enter:");
		Scanner scInt = new Scanner(System.in).useDelimiter("\r\n");
		
		// adding user input to array list
		while (scInt.hasNextInt()) {
			int i = scInt.nextInt();
			userNums.add(i);
		}
		scInt.close();

		// converting array list to regular array
		Integer[] userNumsToArray = userNums.toArray(new Integer[0]);
		
		// printing the user input	
		System.out.println("You entered: ");
		int userNumsLen = userNumsToArray.length;
		for (int i = 0; i < userNumsLen; i++) {
			System.out.printf(userNumsToArray[i] +  " ");
		}
		System.out.println("\n");
		
		// THREADING //
		
		ThreadAverage t1 = new ThreadAverage(userNumsToArray);
		ThreadMinimum t2 = new ThreadMinimum(userNumsToArray);
		ThreadMaximum t3 = new ThreadMaximum(userNumsToArray);
		ThreadMedian t4 = new ThreadMedian(userNumsToArray);
		ThreadStandardDeviation t5 = new ThreadStandardDeviation(userNumsToArray);

		try {
		t1.start();
		t1.join();
		
		t2.start();
		t2.join();
		
		t3.start();
		t3.join();
		
		t4.start();
		t4.join();
		
		t5.start();
		t5.join();
		}
		catch (Exception e){
			System.out.println("An error has occurred");
		}

		// getting the values from the threads and printing them
		System.out.println("Average value: " + t1.show()); 
		System.out.println("Minimum value: " + t2.show());
		System.out.println("Maximum value: " + t3.show());
		System.out.println("Median value: " + t4.show());
		System.out.println("Standard Deviation value: " + t5.show());
	}
}