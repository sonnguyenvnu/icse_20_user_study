/** 
 * @return whether this node has an input with the given name
 */
protected boolean hasInput(String name){
  if (mInputs == null) {
    return false;
  }
  return mInputs.containsKey(name);
}
