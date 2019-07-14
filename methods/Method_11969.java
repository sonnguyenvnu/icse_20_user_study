private String[] copyArray(String[] args,int from,int to){
  String[] result=new String[to - from];
  for (int j=from; j != to; ++j) {
    result[j - from]=args[j];
  }
  return result;
}
