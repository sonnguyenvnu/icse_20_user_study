/** 
 * Returns the index of the track with the given format in the group. The format is located by identity so, for example,  {@code group.indexOf(group.getFormat(index)) == index} even ifmultiple tracks have formats that contain the same values.
 * @param format The format.
 * @return The index of the track, or {@link C#INDEX_UNSET} if no such track exists.
 */
@SuppressWarnings("ReferenceEquality") public int indexOf(Format format){
  for (int i=0; i < formats.length; i++) {
    if (format == formats[i]) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}
