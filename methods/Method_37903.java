/** 
 * Removes all attributes from the request as well as clears entries in this map.
 */
@Override public void clear(){
  entries=null;
  Iterator<String> keys=getAttributeNames();
  while (keys.hasNext()) {
    removeAttribute(keys.next());
  }
}
