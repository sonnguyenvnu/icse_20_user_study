public int getAttributeMask(){
  int mask=0;
  for (  Cycle cycle : myCycles) {
    mask|=1 << cycle.myModel.mAttrIndex;
  }
  return mask;
}
