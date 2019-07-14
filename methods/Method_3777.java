private void ensureViewSet(){
  if (mSet == null || mSet.length != mSpanCount) {
    mSet=new View[mSpanCount];
  }
}
