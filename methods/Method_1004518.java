private static String decapitalize(String str){
  if (str == null) {
    return "";
  }
  String firstLetter=str.substring(0,1).toLowerCase();
  String restLetters=str.substring(1);
  return firstLetter + restLetters;
}
