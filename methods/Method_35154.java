private static void executeChange(@Nullable final Controller to,@Nullable final Controller from,final boolean isPush,@Nullable final ViewGroup container,@Nullable final ControllerChangeHandler inHandler,@NonNull final List<ControllerChangeListener> listeners){
  if (container != null) {
    final ControllerChangeHandler handler;
    if (inHandler == null) {
      handler=new SimpleSwapChangeHandler();
    }
 else     if (inHandler.hasBeenUsed && !inHandler.isReusable()) {
      handler=inHandler.copy();
    }
 else {
      handler=inHandler;
    }
    handler.hasBeenUsed=true;
    if (from != null) {
      if (isPush) {
        completeHandlerImmediately(from.getInstanceId());
      }
 else {
        abortOrComplete(from,to,handler);
      }
    }
    if (to != null) {
      inProgressChangeHandlers.put(to.getInstanceId(),new ChangeHandlerData(handler,isPush));
    }
    for (    ControllerChangeListener listener : listeners) {
      listener.onChangeStarted(to,from,isPush,container,handler);
    }
    final ControllerChangeType toChangeType=isPush ? ControllerChangeType.PUSH_ENTER : ControllerChangeType.POP_ENTER;
    final ControllerChangeType fromChangeType=isPush ? ControllerChangeType.PUSH_EXIT : ControllerChangeType.POP_EXIT;
    final View toView;
    if (to != null) {
      toView=to.inflate(container);
      to.changeStarted(handler,toChangeType);
    }
 else {
      toView=null;
    }
    final View fromView;
    if (from != null) {
      fromView=from.getView();
      from.changeStarted(handler,fromChangeType);
    }
 else {
      fromView=null;
    }
    handler.performChange(container,fromView,toView,isPush,new ControllerChangeCompletedListener(){
      @Override public void onChangeCompleted(){
        if (from != null) {
          from.changeEnded(handler,fromChangeType);
        }
        if (to != null) {
          inProgressChangeHandlers.remove(to.getInstanceId());
          to.changeEnded(handler,toChangeType);
        }
        for (        ControllerChangeListener listener : listeners) {
          listener.onChangeCompleted(to,from,isPush,container,handler);
        }
        if (handler.forceRemoveViewOnPush && fromView != null) {
          ViewParent fromParent=fromView.getParent();
          if (fromParent != null && fromParent instanceof ViewGroup) {
            ((ViewGroup)fromParent).removeView(fromView);
          }
        }
        if (handler.removesFromViewOnPush() && from != null) {
          from.setNeedsAttach(false);
        }
      }
    }
);
  }
}
