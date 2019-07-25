public static String capitalize(final String str){
  int strLen;
  if (str == null || (strLen=str.length()) == 0) {
    return str;
  }
  char firstChar=str.charAt(0);
  if (Character.isTitleCase(firstChar)) {
    return str;
  }
  return new StringBuilder(strLen).append(Character.toTitleCase(firstChar)).append(str.substring(1)).toString();
}
