/** 
 * Add this model to the given controller if the condition is true. Can only be called from inside {@link EpoxyController#buildModels()}.
 */
public void addIf(boolean condition,@NonNull EpoxyController controller){
  if (condition) {
    addTo(controller);
  }
 else   if (controllerToStageTo != null) {
    controllerToStageTo.clearModelFromStaging(this);
    controllerToStageTo=null;
  }
}
