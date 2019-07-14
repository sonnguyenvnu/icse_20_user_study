/** 
 * Returns whether  {@link #encoderDelay} and {@link #encoderPadding} have been set.
 */
public boolean hasGaplessInfo(){
  return encoderDelay != Format.NO_VALUE && encoderPadding != Format.NO_VALUE;
}
