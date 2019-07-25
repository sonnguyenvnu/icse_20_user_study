public static String uncapitalize(final String str){
  int strLen;
  if (str == null || (strLen=str.length()) == 0) {
    return str;
  }
  char firstChar=str.charAt(0);
  if (Character.isLowerCase(firstChar)) {
    return str;
  }
  return new StringBuilder(strLen).append(Character.toLowerCase(firstChar)).append(str.substring(1)).toString();
}
