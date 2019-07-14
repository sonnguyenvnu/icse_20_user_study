/** 
 * Return a copy of the internal keys array. This array can be modified.
 * @webref doubledict:method
 * @brief Return a copy of the internal keys array
 */
public String[] keyArray(){
  crop();
  return keyArray(null);
}
