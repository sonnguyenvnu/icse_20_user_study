@NonNull public Set<E> buildUnmodifiable(){
  Set<E> Set=Collections.unmodifiableSet(mSet);
  mSet=null;
  return Set;
}
