/** 
 * ????????
 * @param path ??
 * @param from ???????base[0]=1?
 * @return ?????????????
 */
public int transition(String path,int from){
  int b=from;
  int p;
  for (int i=0; i < path.length(); ++i) {
    p=b + (int)(path.charAt(i)) + 1;
    if (b == check[p])     b=base[p];
 else     return -1;
  }
  p=b;
  return p;
}
