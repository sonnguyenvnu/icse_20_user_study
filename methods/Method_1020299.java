public void insert(Layer l,int index){
  if ((index < 0) || (index > nlayers) || (exist(l) && (index >= nlayers))) {
    throw new IndexOutOfBoundsException();
  }
  removeImpl(l);
  addImpl(l,index);
}
