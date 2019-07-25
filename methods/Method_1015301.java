private static String spaces(int count){
  StringBuilder result=new StringBuilder();
  for (int i=0; i < count; i++) {
    result.append(" ");
  }
  return result.toString();
}
