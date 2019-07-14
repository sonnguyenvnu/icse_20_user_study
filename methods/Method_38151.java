public void add(final int index){
  indices=Arrays.copyOf(indices,indices.length + 1);
  indices[indices.length - 1]=index;
}
