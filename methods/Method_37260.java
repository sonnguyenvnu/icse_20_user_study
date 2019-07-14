@SuppressWarnings({"unchecked"}) protected void ensureListSize(final List list,final int size){
  int len=list.size();
  while (size >= len) {
    list.add(null);
    len++;
  }
}
