/** 
 * Add content parameter, identified by the given key, to the request.
 * @param key   entity's name
 * @param value entity's value (Scalar, FileWrapper, StreamWrapper)
 */
public void addPart(String key,Object value){
  jsonParams.put(key,value);
}
