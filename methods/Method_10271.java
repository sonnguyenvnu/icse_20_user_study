/** 
 * Will, before sending, remove all headers currently present in AsyncHttpClient instance, which applies on all requests this client makes
 */
public void removeAllHeaders(){
  clientHeaderMap.clear();
}
