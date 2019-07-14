/** 
 * Returns the index of the try catch block (using the order in which they are visited with visitTryCatchBlock), whose 'catch' type is referenced by this type reference. This method must only be used for type references whose sort is  {@link #EXCEPTION_PARAMETER} .
 * @return the index of an exception in the 'throws' clause of a method.
 */
public int getTryCatchBlockIndex(){
  return (targetTypeAndInfo & 0x00FFFF00) >> 8;
}
