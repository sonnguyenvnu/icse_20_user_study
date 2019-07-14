static final public int[] parseInt(boolean what[]){
  int list[]=new int[what.length];
  for (int i=0; i < what.length; i++) {
    list[i]=what[i] ? 1 : 0;
  }
  return list;
}
