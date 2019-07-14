SparseFloatArray getOrCreateFloatProps(){
  if (mFloatProps == null) {
    mFloatProps=new SparseFloatArray(2);
  }
  return mFloatProps;
}
