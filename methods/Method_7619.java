private void init(){
  if (superListenerField != null) {
    try {
      mSuperScrollListener=(ViewTreeObserver.OnScrollChangedListener)superListenerField.get(this);
      superListenerField.set(this,NOP);
    }
 catch (    Exception e) {
      mSuperScrollListener=null;
    }
  }
}
