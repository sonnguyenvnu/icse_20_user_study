/** 
 * @param list ????
 * @return ?????????? <code>lowest common multiple (LCM)</code>
 */
public static int lcms(int... list){
  if (list.length == 0)   return Integer.MAX_VALUE;
  if (list.length == 1) {
    return list[0];
  }
  int lcm=lcm(list[0],list[1]);
  for (int i=2; i < list.length; i++) {
    lcm=lcm(lcm,list[i]);
  }
  return lcm;
}
