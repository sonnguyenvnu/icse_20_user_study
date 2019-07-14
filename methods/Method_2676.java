/** 
 * ????????
 * @param path
 * @return
 */
protected int transition(char[] path){
  int b=base[0];
  int p;
  for (int i=0; i < path.length; ++i) {
    p=b + (int)(path[i]) + 1;
    if (b == check[p])     b=base[p];
 else     return -1;
  }
  p=b;
  return p;
}
