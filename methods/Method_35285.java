private void addChild(final int index){
  @IdRes final int frameId=getResources().getIdentifier("child_content_" + (index + 1),"id",getActivity().getPackageName());
  final ViewGroup container=(ViewGroup)getView().findViewById(frameId);
  final Router childRouter=getChildRouter(container).setPopsLastView(true);
  if (!childRouter.hasRootController()) {
    ChildController childController=new ChildController("Child Controller #" + index,ColorUtil.getMaterialColor(getResources(),index),false);
    childController.addLifecycleListener(new LifecycleListener(){
      @Override public void onChangeEnd(      @NonNull Controller controller,      @NonNull ControllerChangeHandler changeHandler,      @NonNull ControllerChangeType changeType){
        if (!isBeingDestroyed()) {
          if (changeType == ControllerChangeType.PUSH_ENTER && !hasShownAll) {
            if (index < NUMBER_OF_CHILDREN - 1) {
              addChild(index + 1);
            }
 else {
              hasShownAll=true;
            }
          }
 else           if (changeType == ControllerChangeType.POP_EXIT) {
            if (index > 0) {
              removeChild(index - 1);
            }
 else {
              getRouter().popController(ParentController.this);
            }
          }
        }
      }
    }
);
    childRouter.setRoot(RouterTransaction.with(childController).pushChangeHandler(new FadeChangeHandler()).popChangeHandler(new FadeChangeHandler()));
  }
}
