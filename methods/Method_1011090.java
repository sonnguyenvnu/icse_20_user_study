@Override public T last(){
  if (getList().size() > 0) {
    return getList().get(getList().size() - 1);
  }
  if (Sequence.NULL_WHEN_EMPTY) {
    return null;
  }
 else {
    throw new IndexOutOfBoundsException("Empty list");
  }
}
