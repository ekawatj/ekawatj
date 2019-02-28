package com.ekawatj.common.demo;

public class QuestionFour {

  /**
   * Converts integer into String without using library calls.
   * The conversion is done based on the input 'base' value: 8-octal, 10-decimal, or 16-hexadecimal.
   * <p>
   * Assumptions:
   * - Input 'base' can only be either 8, 10, or 16.
   * - Input integer can only be signed for base 10. For other bases, it'll be considered unsigned.
   *
   * @param  input  integer to be converted
   * @param  base   base to use in conversion (8, 10, or 16 only)
   * @return        converted value in form of String
   *
   * @author  Ekko Jirapongpan
   */
  public static String itoa(int input, int base) {
    if (input == 0) return "0";
    // Assumption 1: base in (8, 10, 16)
    if (base != 8 && base != 10 && base != 16) return "Invalid base value: "+base +"\nPlease use either 8, 10, or 16";

    boolean isNegative = false;

    //Max value of integer has 10 digits in decimal, plus a possible '-' (2147483647)
    //11 digits in octal (17 777 777 777)
    //8 digits in hex (7f fff fff)
    Character[] cArr = new Character[11];

    // Assumption 2: number can be signed only for base 10, unsigned for other bases.
    if (input < 0) {
      if (base == 10) isNegative = true;
      input = (input==Integer.MIN_VALUE) ? Integer.MAX_VALUE : -input;
    }

    //Recursively divide the value with the base and process the remainder value accordingly.
    int i=0, remainder=0;
    while (input != 0) {
      remainder = input % base;
      if (remainder > 9) { //for hex over 9 use a-f
        cArr[i] = (char)((remainder-10) + 'a');
      } else { //for octal and decimal
        cArr[i] = (char)(remainder + '0'); //'0'=48
      }
      input = input / base; //remainder is always rounded down for int
      i++;
    }

    if (isNegative) //Add sign back if input was negative base 10
      cArr[i] = '-';

    String result = "";
    //Since we processed the input digits from the right but populated the array from the left, flip the result.
    for (int k = cArr.length-1; k >= 0; k--) {
      if (cArr[k] != null)
        result += cArr[k];
    }

    return result;
  }

  public static void main(String[] args) {

    int[] input = new int[]{0, 1, Integer.MAX_VALUE, Integer.MIN_VALUE, -42};
    String[] expected_oct = new String[]{"0", "1", "17777777777", "17777777777", "52"};
    String[] expected_dec = new String[]{"0", "1", "2147483647", "-2147483647", "-42"};
    String[] expected_hex = new String[]{"0", "1", "7fffffff", "7fffffff", "2a"};

    String expected, result;
    for (int k = 0; k < input.length; k++) {
      //Test Octal
      expected = expected_oct[k];
      result = itoa(input[k], 8);
      System.out.println("OCTAL");
      System.out.println("Expecting: "+ expected + ", Actual: " + result);
      assert expected.equals(result)
              : "\nWrong answer for " + input[k] + ". Expecting: " + expected + " but was: " + result;

      //Test Decimal
      expected = expected_dec[k];
      result = itoa(input[k], 10);
      System.out.println("DECIMAL");
      System.out.println("Expecting: "+ expected + ", Actual: " + result);
      assert expected.equals(result)
              : "\nWrong answer for " + input[k] + ". Expecting: " + expected + " but was: " + result;

      //Test Hex
      expected = expected_hex[k];
      result = itoa(input[k], 16);
      System.out.println("HEXADECIMAL");
      System.out.println("Expecting: "+ expected + ", Actual: " + result);
      assert expected.equals(result)
              : "\nWrong answer for " + input[k] + ". Expecting: " + expected + " but was: " + result;
    }

    //Test base validation
    assert "Invalid base value: 99\nPlease use either 8, 10, or 16".equals(itoa(99, 99)) : "Base validation not working";

  }
}
