public void setScopedContext(ComponentContext scopedContext){
  mScopedContext=scopedContext;
  if (mLayoutCreatedInWillRender != null) {
    assertSameBaseContext(scopedContext,mLayoutCreatedInWillRender.getContext());
  }
}
