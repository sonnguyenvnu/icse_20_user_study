@NonNull public List<E> buildUnmodifiable(){
  List<E> List=Collections.unmodifiableList(mList);
  mList=null;
  return List;
}
