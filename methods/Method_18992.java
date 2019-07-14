private void logDeleteIterative(int index,int count){
  for (int i=0; i < count; i++) {
    mSectionsDebugLogger.logDelete(mSectionTreeTag,index + i,Thread.currentThread().getName());
  }
}
