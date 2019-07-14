@Override public int computeHorizontalScrollOffset(){
  return (int)(mViewBounds.left - mTransformedImageBounds.left);
}
