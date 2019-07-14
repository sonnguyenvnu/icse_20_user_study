/** 
 * Returns the index of the formal parameter whose type is referenced by this type reference. This method must only be used for type references whose sort is  {@link #METHOD_FORMAL_PARAMETER}.
 * @return a formal parameter index.
 */
public int getFormalParameterIndex(){
  return (targetTypeAndInfo & 0x00FF0000) >> 16;
}
