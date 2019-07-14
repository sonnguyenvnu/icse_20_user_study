void dispatchLoadingEvent(LoadingEvent event){
  final LoadingEvent.LoadingState loadingState=event.loadingState;
  if (mLoadEventsHandler != null) {
    final boolean isEmpty=event.isEmpty;
switch (loadingState) {
case FAILED:
      mLoadEventsHandler.onLoadFailed(isEmpty);
    break;
case INITIAL_LOAD:
  mLoadEventsHandler.onInitialLoad();
break;
case LOADING:
mLoadEventsHandler.onLoadStarted(isEmpty);
break;
case SUCCEEDED:
mLoadEventsHandler.onLoadSucceeded(isEmpty);
break;
}
}
postLoadingStateToFocusDispatch(loadingState);
}
