private static String joinWithSeparator(String first,String second){
  return first.length() == 0 ? second : (second.length() == 0 ? first : first + ", " + second);
}
