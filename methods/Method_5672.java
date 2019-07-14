@Override @SuppressWarnings("ReferenceEquality") public final int indexOf(Format format){
  for (int i=0; i < length; i++) {
    if (formats[i] == format) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}
