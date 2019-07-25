@Override public DataElement next(){
  return (_preOrder ? preOrderNext() : postOrderNext());
}
