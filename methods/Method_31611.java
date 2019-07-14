private boolean endsWith(StringBuilder result,String str){
  if (result.length() < str.length()) {
    return false;
  }
  for (int i=0; i < str.length(); i++) {
    if (result.charAt(result.length() - str.length() + i) != str.charAt(i)) {
      return false;
    }
  }
  return true;
}
