/** 
 * override the abstract implementation because that is not stable in concurrent requests
 */
public boolean contains(String key){
  if (key == null)   return false;
  Entry e=this.get(key);
  return e == null ? false : e.enabled();
}
