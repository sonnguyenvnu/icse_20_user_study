/** 
 * ???????????
 */
private static synchronized void poolInit(){
  if (null == jedisPool) {
    initialPool();
  }
}
