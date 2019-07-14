/** 
 * Returns whether or not the transition should create an Animator, based on the values captured during  {@link #captureStartValues(TransitionValues)} and{@link #captureEndValues(TransitionValues)}. The default implementation compares the property values returned from  {@link #getTransitionProperties()}, or all property values if {@code getTransitionProperties()} returns null. Subclasses may override this method toprovide logic more specific to the transition implementation.
 * @param startValues the values from captureStartValues, This may be {@code null} if theView did not exist in the start state.
 * @param endValues the values from captureEndValues. This may be {@code null} if the Viewdid not exist in the end state.
 */
public boolean isTransitionRequired(@Nullable TransitionValues startValues,@Nullable TransitionValues endValues){
  boolean valuesChanged=false;
  if (startValues != null && endValues != null) {
    String[] properties=getTransitionProperties();
    if (properties != null) {
      int count=properties.length;
      for (int i=0; i < count; i++) {
        if (isValueChanged(startValues,endValues,properties[i])) {
          valuesChanged=true;
          break;
        }
      }
    }
 else {
      for (      String key : startValues.values.keySet()) {
        if (isValueChanged(startValues,endValues,key)) {
          valuesChanged=true;
          break;
        }
      }
    }
  }
  return valuesChanged;
}
