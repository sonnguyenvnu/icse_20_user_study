private void resetAdapter(){
  if (mCurrentAdapter != null) {
    mCurrentAdapter.shutDown();
    mCurrentAdapter=null;
    System.gc();
  }
}
