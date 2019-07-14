/** 
 * Destroys this session monitor by removing all collected resources.
 */
public void destroy(){
  listeners.clear();
  sessionMap.clear();
}
