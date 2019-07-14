public static boolean isNumer(String number){
  if (isNotEmpty(number,true) == false) {
    return false;
  }
  Pattern pattern=Pattern.compile("[0-9]*");
  Matcher isNum=pattern.matcher(number);
  if (!isNum.matches()) {
    return false;
  }
  currentString=number;
  return true;
}
