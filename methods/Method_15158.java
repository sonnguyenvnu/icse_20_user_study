/** 
 * ????Handler
 * @param name
 * @return
 */
private Handler getHandler(String name){
  ThreadBean tb=getThread(name);
  return tb == null ? null : tb.getHandler();
}
