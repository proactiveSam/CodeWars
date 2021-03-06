package pers.sam.practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * https://www.codewars.com/kata/55b195a69a6cc409ba000053/train/java
 * brute force...
 * this method is not a good one
 * @author Sam Lee
 * @version 2016��11��5������6:21:02
 */
public class TotalIncreasingOrDecreasingNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("totalIncDec(0) : "+totalIncDec(0));
		System.out.println("totalIncDec(1) : "+totalIncDec(1));
		System.out.println("totalIncDec(2) : "+totalIncDec(2));
		System.out.println("totalIncDec(3) : "+totalIncDec(3));
		System.out.println("totalIncDec(4) : "+totalIncDec(4));
		System.out.println("totalIncDec(5) : "+totalIncDec(5));
		System.out.println("totalIncDec(6) : "+totalIncDec(6));
//		System.out.println("totalIncDec(10) : "+totalIncDec(100));
			
//		System.out.println("totalIncDec(115) : "+totalIncDec(115));
		
//		bruteForceTester(100,1000);
		
		
//		System.out.println(isIncDecNumber(1,"100"));
//		System.out.println(isIncDecNumber(4,"2345"));
//		System.out.println(isIncDecNumber(1,"2345"));
//		System.out.println(isIncDecNumber(1,"55"));
//		System.out.println(isIncDecNumber(7,"642"));
	}
	
	/**
	 * 1) init n array
	 * 2) for each digit numbers k (ie, k=1,num<10; k=2,num<100; k=3,100) 
	 * 3)     find decrease numbers base on k-1 result in array;
	 * 5)     find increase numbers base on k-1 result In array;
	 * 6) return sum;
	 * @param n
	 * @return
	 */
	public static BigInteger totalIncDec(int n) {
		
//		System.out.println(Math.pow(10, n));
		
		Map<Integer,List> digitsNumberMap = new HashMap<>();
		
//		int digits = String.valueOf(n).length();
//		System.out.println(digits);
		
		if(n==0){
			return new BigInteger("1");
		}else if(n==1){
			return new BigInteger("10");
		}
		
		// 0 digits
		List<String> zeroDigits = new ArrayList<>();
		zeroDigits.add(0+"");
		digitsNumberMap.put(0, zeroDigits);
		
		//1 digits
		List<String> oneDigits = new ArrayList<>();
		for(int k=0;k<10;k++){
			oneDigits.add("0"+k);
			}
		digitsNumberMap.put(1, oneDigits);
		
		//2 digits
		List<String> twoDigits  = new ArrayList<>();
		for(int k=10;k<100;k++){
			twoDigits.add(k+"");
		}
		digitsNumberMap.put(2, twoDigits);
		
		//>=3 digits
		for(int i = 3;i<=n;i++){
			// i digits,  for example i=1,num<10
			
			List<String> lastDigits = digitsNumberMap.get(i-1);
			List<String> currentDigits = new ArrayList<>();
			for(int j=0;j<lastDigits.size();j++){
				String smallNum = lastDigits.get(j);
				
				addDigits(currentDigits, smallNum);
			}
			for(int k = 1;k<=9;k++){
				String num0 =padding0(k,i);
				currentDigits.add(num0);
			}
			
//			System.out.println(i+" "+currentDigits.size());
			digitsNumberMap.put(i, currentDigits);
		}
		
		int resultCount = 0;
		
		for(Iterator<Integer> it = digitsNumberMap.keySet().iterator();it.hasNext();){
			Integer digit = it.next();
			List<String> currentDigits = digitsNumberMap.get(digit);
			if(digit ==0){
				continue;
			}
			
//			bi.add(new BigInteger(""+currentDigits.size()));
			resultCount = resultCount + currentDigits.size();
//			System.out.println("digits = "+digit+" "+currentDigits.size());
		}
		
//		System.out.println("-----");
//		List<String> tempDigits = digitsNumberMap.get(3);
//		List<Integer> intList = new ArrayList<>();
// 		for(int i = 0;i<tempDigits.size();i++){
//			intList.add(Integer.valueOf(tempDigits.get(i)));
//		}
// 		Collections.sort(intList);
// 		for(int i = 0;i<intList.size();i++){
//			System.out.println(intList.get(i));
//		}
 		
 		
//		for(Iterator<Integer> it = digitsNumberMap.keySet().iterator();it.hasNext();){
//			Integer digit = (Integer) it.next();
//			List<String> currentDigits = digitsNumberMap.get(digit);
//			System.out.print("Digit "+digit+" :");
//			for(String d:currentDigits){
//				System.out.print(d+" ");
//			}
//			System.out.println();
//		}
		
		return new BigInteger(String.valueOf(resultCount));
	}

	private static void addDigits(List<String> currentDigits, String smallNum) {
		Integer firstDigit = Integer.valueOf(smallNum.substring(0, 1));
		Integer lastDigit = Integer
				.valueOf(smallNum.substring(smallNum.length() -1, smallNum.length()));
		
		if (firstDigit > lastDigit) {
			for (int k = firstDigit; k <= 9; k++) {
				currentDigits.add(k + smallNum);
			}

		} else if (firstDigit < lastDigit) {
			for (int k = 1; k <=firstDigit; k++) {
				currentDigits.add(k + smallNum);
			}
		}else if(firstDigit==lastDigit){
			for (int k = 1; k <=9; k++) {
				currentDigits.add(k + smallNum);
			}
		}
	}
	
	public static String padding0(int k,int n){
		StringBuffer sb = new StringBuffer();
		sb.append(k);
		
		for(int i = 1;i<=n;i++){
			sb.append("0");
		}
		
		return sb.toString();
	}
	
	public static boolean isIncDecNumber(int leftDigit, String smallNumber) {
//		System.out.println(smallNumber);
		if (leftDigit >= Integer.valueOf(smallNumber.substring(0, 1))
				&& Integer.valueOf(smallNumber.substring(0, 1)) >= Integer
						.valueOf(smallNumber.substring(smallNumber.length() -1, smallNumber.length()))) {
			return true;
		} else if (leftDigit <= Integer.valueOf(smallNumber.substring(0, 1))
				&& Integer.valueOf(smallNumber.substring(0, 1)) <= Integer
				.valueOf(smallNumber.substring(smallNumber.length() - 1, smallNumber.length()))) {
			return true;
		}

		return false;
	}

	/**
	 * for test
	 * @param a
	 * @param b
	 */
	public static void bruteForceTester(int a,int b){
		
		for(int i = a;i<b;i++){
			if(isIncDecNumber(i)){
				System.out.println(i);
			}
		}
		
	}
	
	public static boolean isIncDecNumber(Integer num){
		
		String str = num+"";
		
		List<Integer> intList = new ArrayList<Integer>();
		for(int i = 0;i<str.length();i++){
			intList.add(Integer.valueOf(str.substring(i, i+1)));
		}
		
		boolean flag = true;
		
		int leftNum = intList.get(0);
		for(int i = 0;i<intList.size();i++){
			if(leftNum>=intList.get(i)){
				leftNum = intList.get(i);
			}else{
				flag = false;
				break;
			}
		}
		
		if(flag){
			return flag;
		}
		flag = true;
		leftNum = intList.get(0);
		for(int i = 0;i<intList.size();i++){
			if(leftNum<=intList.get(i)){
				leftNum = intList.get(i);
			}else{
				flag = false;
				break;
			}
		}
		if(flag){
			return flag;
		}
		return false;
	}
	
}
