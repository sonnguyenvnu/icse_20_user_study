/** 
 * Returns the length of the array pointed to by this Array Mirror
 * @return The length of the array.
 */
public int length(){
  return v8Object.executeIntegerFunction(LENGTH,null);
}
