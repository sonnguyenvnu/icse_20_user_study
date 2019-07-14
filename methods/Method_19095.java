private void postLoadingStateToFocusDispatch(final LoadingEvent.LoadingState loadingState){
  if (isMainThread()) {
    setLoadingStateToFocusDispatch(loadingState);
  }
 else {
    String tag=EMPTY_STRING;
    if (mMainThreadHandler.isTracing()) {
      tag="SectionTree.postLoadingStateToFocusDispatch - " + loadingState.name() + " - " + mTag;
    }
    mMainThreadHandler.post(new Runnable(){
      @Override public void run(){
        setLoadingStateToFocusDispatch(loadingState);
      }
    }
,tag);
  }
}
