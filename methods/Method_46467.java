/** 
 * ???????
 */
public static void makeUnProxy(){
  if (currentLocal.get() != null) {
    cur().proxyTmp=cur().proxy;
    cur().proxy=false;
  }
}
