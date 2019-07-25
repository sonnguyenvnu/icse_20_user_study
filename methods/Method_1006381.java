private int advance(ShortIterator it){
  if (it.hasNext()) {
    return toIntUnsigned(it.next());
  }
 else {
    return -1;
  }
}
