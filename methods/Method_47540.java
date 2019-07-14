public int getDimension(@AttrRes int attrId){
  TypedArray ta=getTypedArray(attrId);
  int dim=ta.getDimensionPixelSize(0,0);
  ta.recycle();
  return dim;
}
