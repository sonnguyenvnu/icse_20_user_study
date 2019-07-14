/** 
 * Calculates the number of decimal digits for the given value, including the sign.
 */
public static int calculateDigitCount(long value){
  if (value < 0) {
    if (value != Long.MIN_VALUE) {
      return calculateDigitCount(-value) + 1;
    }
 else {
      return 20;
    }
  }
  return (value < 10 ? 1 : (value < 100 ? 2 : (value < 1000 ? 3 : (value < 10000 ? 4 : ((int)(Math.log(value) / LOG_10) + 1)))));
}
