/** 
 * Unsafe version of  {@link #mWeights() mWeights}. 
 */
public static DoubleBuffer nmWeights(long struct){
  return memDoubleBuffer(memGetAddress(struct + AIMeshMorphKey.MWEIGHTS),nmNumValuesAndWeights(struct));
}
