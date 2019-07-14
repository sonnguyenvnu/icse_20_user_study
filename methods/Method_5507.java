/** 
 * Returns the index of a group within the array.
 * @param group The group.
 * @return The index of the group, or {@link C#INDEX_UNSET} if no such group exists.
 */
@SuppressWarnings("ReferenceEquality") public int indexOf(TrackGroup group){
  for (int i=0; i < length; i++) {
    if (trackGroups[i] == group) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}
