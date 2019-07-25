/** 
 * Computes the weighted "closeness" of another Panose to this value.
 * @param otherPanose Another Panose instance which is being compared to this.
 * @param weights 10-element byte array of weights that should be used for each of the elementsin the comparison. Values in this array must be between 0 and 127 inclusive. (This constant is documented at http://www.w3.org/Fonts/Panose/pan2.html#StaticDigits). Use null if all elements are to be weighted equally.
 * @return The weighted difference between the two Panose values.A smaller value indicates that the two values are closer, and a larger value indicates that they are farther apart.
 */
public long difference(final Panose otherPanose,final byte[] weights){
  byte[] weightsToUse=null;
  if (weights == null) {
    weightsToUse=Panose.NEUTRAL_WEIGHTS;
  }
 else {
    validateWeights(weights);
    weightsToUse=weights;
  }
  long difference=0;
  for (int i=0; i < Panose.Field.values().length; i++) {
    final int weight=weightsToUse[i];
    final int thisDifference=this.getElement(i) - otherPanose.getElement(i);
    difference+=weight * thisDifference * thisDifference;
  }
  return difference;
}
