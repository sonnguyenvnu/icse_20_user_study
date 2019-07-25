/** 
 * Does the name marked already exist?
 */
public boolean exists(String name){
  if (name == null) {
    return false;
  }
  return markerMap.containsKey(name);
}
