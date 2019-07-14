/** 
 * This is used internally by generated models to turn on validation checking when "validateEpoxyModelUsage" is enabled and the model is used with an  {@link EpoxyController}.
 */
protected final void addWithDebugValidation(@NonNull EpoxyController controller){
  if (controller == null) {
    throw new IllegalArgumentException("Controller cannot be null");
  }
  if (controller.isModelAddedMultipleTimes(this)) {
    throw new IllegalEpoxyUsage("This model was already added to the controller at position " + controller.getFirstIndexOfModelInBuildingList(this));
  }
  if (firstControllerAddedTo == null) {
    firstControllerAddedTo=controller;
    hashCodeWhenAdded=hashCode();
    controller.addAfterInterceptorCallback(new ModelInterceptorCallback(){
      @Override public void onInterceptorsStarted(      EpoxyController controller){
        currentlyInInterceptors=true;
      }
      @Override public void onInterceptorsFinished(      EpoxyController controller){
        hashCodeWhenAdded=EpoxyModel.this.hashCode();
        currentlyInInterceptors=false;
      }
    }
);
  }
}
