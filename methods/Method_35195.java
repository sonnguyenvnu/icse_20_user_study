void destroy(boolean popViews){
  popsLastView=true;
  final List<RouterTransaction> poppedControllers=backstack.popAll();
  trackDestroyingControllers(poppedControllers);
  if (popViews && poppedControllers.size() > 0) {
    RouterTransaction topTransaction=poppedControllers.get(0);
    topTransaction.controller().addLifecycleListener(new LifecycleListener(){
      @Override public void onChangeEnd(      @NonNull Controller controller,      @NonNull ControllerChangeHandler changeHandler,      @NonNull ControllerChangeType changeType){
        if (changeType == ControllerChangeType.POP_EXIT) {
          for (int i=poppedControllers.size() - 1; i > 0; i--) {
            RouterTransaction transaction=poppedControllers.get(i);
            performControllerChange(null,transaction,true,new SimpleSwapChangeHandler());
          }
        }
      }
    }
);
    performControllerChange(null,topTransaction,false,topTransaction.popChangeHandler());
  }
}
