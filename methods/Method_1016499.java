@Override public RotateIterator<E> clone(final Object modifier){
  return new RotateIterator<E>(this.a,modifier,this.terminationCount - 1);
}
