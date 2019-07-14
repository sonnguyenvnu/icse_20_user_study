public static String capitalizeFirstLetter(String s){
  if (isEmpty(s)) {
    return "";
  }
  char first=s.charAt(0);
  if (Character.isUpperCase(first)) {
    return s;
  }
 else {
    return Character.toUpperCase(first) + s.substring(1);
  }
}
