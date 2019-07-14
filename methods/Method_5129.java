@Override public final void releaseSource(SourceInfoRefreshListener listener){
  sourceInfoListeners.remove(listener);
  if (sourceInfoListeners.isEmpty()) {
    looper=null;
    timeline=null;
    manifest=null;
    releaseSourceInternal();
  }
}
