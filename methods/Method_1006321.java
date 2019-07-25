@Override public Bitmap ior(Bitmap other){
  bitmap.or(((RoaringBitmapWrapper)other).bitmap);
  return this;
}
