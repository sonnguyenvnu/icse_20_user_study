/** 
 * The full signature for setCurrentValue includes the option of not setting the spring at rest after updating its currentValue. Passing setAtRest false means that if the endValue of the spring is not equal to the currentValue, the physics system will start iterating to resolve the spring to the end value. This is almost never the behavior that you want, so the default setCurrentValue signature passes true.
 * @param currentValue the new start and current value for the spring
 * @param setAtRest optionally set the spring at rest after updating its current value.see  {@link com.facebook.rebound.Spring#setAtRest()}
 * @return the spring for chaining
 */
public Spring setCurrentValue(double currentValue,boolean setAtRest){
  mStartValue=currentValue;
  mCurrentState.position=currentValue;
  for (  SpringListener listener : mListeners) {
    listener.onSpringUpdate(this);
  }
  if (setAtRest) {
    setAtRest();
  }
  return this;
}
