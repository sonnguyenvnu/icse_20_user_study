/** 
 * Produce a string from a Number.
 * @param number A Number
 * @return A String.
 * @throws RuntimeException If number is null or a non-finite number.
 */
private static String numberToString(Number number){
  if (number == null) {
    throw new RuntimeException("Null pointer");
  }
  testValidity(number);
  String string=number.toString();
  if (string.indexOf('.') > 0 && string.indexOf('e') < 0 && string.indexOf('E') < 0) {
    while (string.endsWith("0")) {
      string=string.substring(0,string.length() - 1);
    }
    if (string.endsWith(".")) {
      string=string.substring(0,string.length() - 1);
    }
  }
  return string;
}
