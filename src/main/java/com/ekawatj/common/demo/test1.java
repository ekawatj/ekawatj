package com.ekawatj.common.demo;

public class test1 {
  private static final int ASCII_VALUE_OF_ZERO = 48;
  public static void main(String[] args){
    char a = (char)(5+48);
    System.out.println("...char a="+a);
//    int a1 = -1;
//    a = (char)(a + a1);
//    System.out.println("...a1="+ -a1);

    int x = -2147483647;
    char[] ascii = itoa2(x);
    System.out.println(ascii);


  }

  private static char[] itoa2(int number) {
    String result;
    Character[] carr = new Character[]{};
    char c;
    boolean negativeFlag = number < 0 ? true : false;

    number = negativeFlag ? -number : number; //get positive value for processing

    if (number <= 9) {
      c = (char)(number+48);
      return new char[]{0}; //String.valueOf(c); //
    }


    if(number >= 0 && number <= 9) {
      char temp = (char) (ASCII_VALUE_OF_ZERO + number);
      if(!negativeFlag) {
        return new char[] { temp };
      }

      return new char[] { '-', temp };
    }

    // define an array of which can hold 12 characters
    // the max integer is 10 digits long - 1 for negative character
    char[] digits = new char[12];

    // now let's divide the number by 10 and keep adding the remainder
    int digitPosition = 0;

    do {
      int remainder = number % 10;
      number = number / 10;

      digits[digitPosition++] = (char) (ASCII_VALUE_OF_ZERO + remainder);
    } while(number > 0);

    // add negative sign if needed
    if(negativeFlag) {
      digits[digitPosition++] = '-';
    }

    // now reverse the array
    for(int i = 0; i < digitPosition / 2; i++) {
      char temp = digits[i];
      digits[i] = digits[digitPosition - i - 1];
      digits[digitPosition - i - 1] = temp;
    }

    return digits;
  }


}
