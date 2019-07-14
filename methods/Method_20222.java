/** 
 * This is used internally by generated models to do validation checking when "validateEpoxyModelUsage" is enabled and the model is used with an  {@link EpoxyController}. This method validates that it is ok to change this model. It is only valid if the model hasn't yet been added, or the change is being done from an  {@link EpoxyController.Interceptor}callback. <p> This is also used to stage the model for implicitly adding it, if it is an AutoModel and implicit adding is enabled.
 */
protected final void onMutation(){
  if (isDebugValidationEnabled() && !currentlyInInterceptors) {
    throw new ImmutableModelException(this,getPosition(firstControllerAddedTo,this));
  }
  if (controllerToStageTo != null) {
    controllerToStageTo.setStagedModel(this);
  }
}
