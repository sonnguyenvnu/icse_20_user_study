private void postReleaseComponentTreeHolders(final List<ComponentTreeHolder> holders){
  mMainThreadHandler.post(new Runnable(){
    @Override public void run(){
      releaseComponentTreeHolders(holders);
    }
  }
);
}
