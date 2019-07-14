@Deprecated public void setSelectedList(int... poses){
  Set<Integer> set=new HashSet<>();
  for (  int pos : poses) {
    set.add(pos);
  }
  setSelectedList(set);
}
