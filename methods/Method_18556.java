private SparseArray<Object> getOrCreateObjectProps(){
  if (mObjectProps == null) {
    mObjectProps=new SparseArray<>(2);
  }
  return mObjectProps;
}
