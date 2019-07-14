/** 
 * This method checks to see that the current spring displacement value is equal to the input, accounting for the spring's rest displacement threshold.
 * @param value The value to compare the spring value to
 * @return Whether the displacement value from the spring is within the bounds of the comparevalue, accounting for threshold
 */
public boolean currentValueIsApproximately(double value){
  return Math.abs(getCurrentValue() - value) <= getRestDisplacementThreshold();
}
