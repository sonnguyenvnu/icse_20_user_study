@NotNull public ListType toListType(){
  ListType t=new ListType();
  for (  Type e : eltTypes) {
    t.add(e);
  }
  return t;
}
