/** 
 * Get this entire array as a String array.
 * @webref jsonarray:method
 * @brief Gets the entire array as an array of Strings
 * @see JSONArray#getIntArray()
 */
public String[] getStringArray(){
  String[] outgoing=new String[size()];
  for (int i=0; i < size(); i++) {
    outgoing[i]=getString(i);
  }
  return outgoing;
}
