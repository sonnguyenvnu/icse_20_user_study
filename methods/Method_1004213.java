@Override public RandomDataInput bytes(){
  if (!bsInit) {
    bs.writeDouble(0,instance);
    bsInit=true;
  }
  return bs;
}
