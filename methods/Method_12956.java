private static boolean isNumeric(String str){
  Matcher isNum=pattern.matcher(str);
  if (!isNum.matches()) {
    return false;
  }
  return true;
}
