@Override public MappeableContainer ior(MappeableBitmapContainer x){
  if (isFull()) {
    return this;
  }
  return or(x);
}
