package com.ontariotechu.sofe3980U;
/**
 * A class that represents a binary number and provides methods to perform arithmetic operations on binary numbers.
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// // Ensure empty strings are replaced with "0"
		// if (this.number.isEmpty()) {
		// 	this.number = "0";
		// }
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	/**
	 * Perform bitwise OR operation between two binary numbers.
	 * @param num1 The first binary number.
	 * @param num2 The second binary number.
	 * @return A binary object representing the result of the OR operation.
	 */
	public static Binary or(Binary num1, Binary num2) {
		StringBuilder result = new StringBuilder();
		int len1 = num1.number.length();
		int len2 = num2.number.length();
		int maxLen = Math.max(len1, len2);

		for (int i = 0; i < maxLen; i++) {
			char bit1 = (i < maxLen - len1) ? '0' : num1.number.charAt(i - (maxLen - len1));
			char bit2 = (i < maxLen - len2) ? '0' : num2.number.charAt(i - (maxLen - len2));
			result.append((bit1 == '1' || bit2 == '1') ? '1' : '0');
		}
		return new Binary(result.toString());
	}

	/**
	 * Perform bitwise AND operation between two binary numbers.
	 * @param num1 The first binary number.
	 * @param num2 The second binary number.
	 * @return A binary object representing the result of the AND operation.
	 */
		public static Binary and(Binary num1, Binary num2) {
			String  value1 = num1.getValue();
			String  value2 = num2.getValue();
		
			// Pad the shorter string with leading zeros
			if (value1.length() < value2.length()) {
				StringBuilder zeros = new StringBuilder();
				for (int i = 0; i < value2.length() - value1.length(); i++) {
					zeros.append("0");
				}
				value1 = zeros.toString() + value1;
			} else if (value2.length() < value1.length()) {
				StringBuilder zeros = new StringBuilder();
				for (int i = 0; i < value1.length() - value2.length(); i++) {
					zeros.append("0");
				}
				value2 = zeros.toString() + value2;
			}
		
			// Perform bitwise AND
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < value1.length(); i++) {
				result.append(value1.charAt(i) == '1' && value2.charAt(i) == '1' ? '1' : '0');
			}
		
			return new Binary(result.toString());
		}
	

	

	/**
	 * Multiply two binary numbers.
	 * @param num1 The first binary number.
	 * @param num2 The second binary number.
	 * @return A binary object representing the product of the two binary numbers.
	 */
	public static Binary multiply(Binary num1, Binary num2) {
		Binary product = new Binary("0");
		String num2Value = num2.number;

		for (int i = num2Value.length() - 1; i >= 0; i--) {
			if (num2Value.charAt(i) == '1') {
				StringBuilder zeros = new StringBuilder();
				for (int j = 0; j < num2Value.length() - 1 - i; j++) {
					zeros.append("0");
				}
				String shiftedValue = num1.number + zeros.toString();
				product = Binary.add(product, new Binary(shiftedValue));
			}
		}
		return product;
	}


}	
