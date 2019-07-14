/** 
 * ????
 * @param c
 * @param from
 * @return
 */
public int transition(char c,int from){
  int b=from;
  int p;
  p=b + (int)(c) + 1;
  if (b == check[p])   b=base[p];
 else   return -1;
  return b;
}
