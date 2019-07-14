/** 
 * Returns the index of the first adaptation set of a given type, or  {@link C#INDEX_UNSET} if noadaptation set of the specified type exists.
 * @param type An adaptation set type.
 * @return The index of the first adaptation set of the specified type, or {@link C#INDEX_UNSET}.
 */
public int getAdaptationSetIndex(int type){
  int adaptationCount=adaptationSets.size();
  for (int i=0; i < adaptationCount; i++) {
    if (adaptationSets.get(i).type == type) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}
