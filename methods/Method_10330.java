/** 
 * Removes a parameter from the request.
 * @param key the key name for the parameter to remove.
 */
public void remove(String key){
  urlParams.remove(key);
  streamParams.remove(key);
  fileParams.remove(key);
  urlParamsWithObjects.remove(key);
  fileArrayParams.remove(key);
}
