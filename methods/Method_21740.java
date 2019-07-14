private static String removeBrackets(String wkt,int num){
  String result=wkt;
  for (int i=0; i < num; i++) {
    int lastClosingBrackets=result.lastIndexOf(")");
    int firstOpenBrackets=result.indexOf("(");
    if (lastClosingBrackets == -1 || firstOpenBrackets == -1)     throw new IllegalArgumentException("not enough brackets");
    result=result.substring(firstOpenBrackets + 1,lastClosingBrackets);
  }
  return result;
}
