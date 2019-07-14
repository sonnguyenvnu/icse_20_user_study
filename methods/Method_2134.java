/** 
 * Returns true if the transform was corrected during the last update. We should rename this method to `wasTransformedWithoutCorrection` and just return the internal flag directly. However, this requires interface change and negation of meaning.
 */
@Override public boolean wasTransformCorrected(){
  return mWasTransformCorrected;
}
