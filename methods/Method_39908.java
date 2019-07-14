/** 
 * Returns iterator for all stored session IDs. Keep in mind that session may <b>NOT</b> longer exist during the iteration!
 */
public Iterator<String> iterator(){
  return sessionMap.keySet().iterator();
}
