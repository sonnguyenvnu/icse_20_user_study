/** 
 * Unsafe version of  {@link #mWeights(DoubleBuffer) mWeights}. 
 */
public static void nmWeights(long struct,DoubleBuffer value){
  memPutAddress(struct + AIMeshMorphKey.MWEIGHTS,memAddress(value));
}
