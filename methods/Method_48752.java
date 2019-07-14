/** 
 * Long identifier used to compare elements. Often, this is the same as  {@link #longId()}but some instances of elements may be considered the same even if their ids differ. In that case, this method should be overwritten to return an id that can be used for comparison.
 * @return
 */
protected long getCompareId(){
  return longId();
}
