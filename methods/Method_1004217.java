@Override public RandomDataInput bytes(){
  if (!bsInit) {
    bs.writeInt(0,instance);
    bsInit=true;
  }
  return bs;
}
