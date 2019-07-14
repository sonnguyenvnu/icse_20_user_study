SparseIntArray getOrCreateIntProps(){
  if (mIntProps == null) {
    mIntProps=new SparseIntArray(2);
  }
  return mIntProps;
}
