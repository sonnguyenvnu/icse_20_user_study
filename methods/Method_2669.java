public int exactMatchSearch(String key,int pos,int len,int nodePos){
  if (len <= 0)   len=key.length();
  if (nodePos <= 0)   nodePos=0;
  int result=-1;
  int b=base[nodePos];
  int p;
  for (int i=pos; i < len; i++) {
    p=b + (int)(key.charAt(i)) + 1;
    if (b == check[p])     b=base[p];
 else     return result;
  }
  p=b;
  int n=base[p];
  if (b == check[p] && n < 0) {
    result=-n - 1;
  }
  return result;
}
