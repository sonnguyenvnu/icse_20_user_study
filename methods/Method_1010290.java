/** 
 * Internal API, intended for use from StructureAspectDescriptor.
 */
public int index(SConceptId cid){
  long key=cid.getIdValue();
  return myIndex.containsKey(key) ? myIndex.get(key) : -1;
}
