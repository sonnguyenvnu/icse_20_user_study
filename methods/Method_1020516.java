public final void suspend(){
  Preconditions.checkState(mInited,"Not inited");
  onSuspended();
  mInited=false;
}
