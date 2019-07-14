@Override public final int indexOf(int indexInTrackGroup){
  for (int i=0; i < length; i++) {
    if (tracks[i] == indexInTrackGroup) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}
