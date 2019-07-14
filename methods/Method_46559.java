public static String singleLine(String str){
  return lineBreakPattern.matcher(str).replaceAll(" ");
}
