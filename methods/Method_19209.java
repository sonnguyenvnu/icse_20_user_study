public synchronized void updateLayoutHandler(@Nullable LithoHandler layoutHandler){
  mLayoutHandler=layoutHandler;
  if (mComponentTree != null) {
    mComponentTree.updateLayoutThreadHandler(layoutHandler);
  }
}
