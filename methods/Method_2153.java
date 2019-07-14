@Override public int computeVerticalScrollOffset(){
  return (int)(mViewBounds.top - mTransformedImageBounds.top);
}
