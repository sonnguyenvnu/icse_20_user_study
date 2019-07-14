/** 
 * Returns the index of the exception, in a 'throws' clause of a method, whose type is referenced by this type reference. This method must only be used for type references whose sort is  {@link #THROWS}.
 * @return the index of an exception in the 'throws' clause of a method.
 */
public int getExceptionIndex(){
  return (targetTypeAndInfo & 0x00FFFF00) >> 8;
}
