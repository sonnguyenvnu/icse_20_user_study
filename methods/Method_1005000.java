@Override protected RoaringBitmap _apply(final RoaringBitmap a,final RoaringBitmap b){
  a.or(b);
  return a;
}
