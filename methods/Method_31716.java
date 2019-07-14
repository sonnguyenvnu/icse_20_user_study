private static void addToken(List<String> tokens,String str,int start,int end){
  if (start < end) {
    tokens.add(str.substring(start,end));
  }
}
