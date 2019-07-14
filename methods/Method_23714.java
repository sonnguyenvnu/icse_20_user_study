/** 
 * Return a copy of the internal keys array. This array can be modified.
 * @webref stringdict:method
 * @brief Return a copy of the internal keys array
 */
public String[] keyArray(){
  crop();
  return keyArray(null);
}
