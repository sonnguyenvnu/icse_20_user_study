/** 
 * ??????
 */
public static void makeProxy(){
  if (currentLocal.get() != null) {
    cur().proxyTmp=cur().proxy;
    cur().proxy=true;
  }
}
