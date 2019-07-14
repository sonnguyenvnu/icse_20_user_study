/** 
 * ?????????????
 */
public static void undoProxyStatus(){
  if (currentLocal.get() != null) {
    cur().proxy=cur().proxyTmp;
  }
}
