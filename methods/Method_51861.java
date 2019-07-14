@Override @Deprecated public int getArrayDepth(){
  return getArrayDimensionOnType() + getArrayDimensionOnDeclaratorId();
}
