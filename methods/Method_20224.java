/** 
 * This is used internally by generated models to do validation checking when "validateEpoxyModelUsage" is enabled and the model is used with a  {@link EpoxyController}. This method validates that the model's hashCode hasn't been changed since it was added to the controller. This is similar to  {@link #onMutation()}, but that method is only used for specific model changes such as calling a setter. By checking the hashCode, this method allows us to catch more subtle changes, such as through setting a field directly or through changing an object that is set on the model.
 */
protected final void validateStateHasNotChangedSinceAdded(String descriptionOfChange,int modelPosition){
  if (isDebugValidationEnabled() && !currentlyInInterceptors && hashCodeWhenAdded != hashCode()) {
    throw new ImmutableModelException(this,descriptionOfChange,modelPosition);
  }
}
