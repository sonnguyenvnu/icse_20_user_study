public List<Integer> commonPrefixSearch(String key,int pos,int len,int nodePos){
  if (len <= 0)   len=key.length();
  if (nodePos <= 0)   nodePos=0;
  List<Integer> result=new ArrayList<Integer>();
  char[] keyChars=key.toCharArray();
  int b=base[nodePos];
  int n;
  int p;
  for (int i=pos; i < len; i++) {
    p=b;
    n=base[p];
    if (b == check[p] && n < 0) {
      result.add(-n - 1);
    }
    p=b + (int)(keyChars[i]) + 1;
    if (b == check[p])     b=base[p];
 else     return result;
  }
  p=b;
  n=base[p];
  if (b == check[p] && n < 0) {
    result.add(-n - 1);
  }
  return result;
}
