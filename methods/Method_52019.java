/** 
 * Returns true if this qualified name identifies a local class.
 */
@Override public boolean isLocalClass(){
  return localIndices.head() != NOTLOCAL_PLACEHOLDER;
}
