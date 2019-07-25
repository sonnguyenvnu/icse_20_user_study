@Override public Container ior(BitmapContainer x){
  if (isFull()) {
    return this;
  }
  return or(x);
}
