/** 
 * Unsafe version of  {@link #mWeights}. 
 */
public static AIVertexWeight.Buffer nmWeights(long struct){
  return AIVertexWeight.create(memGetAddress(struct + AIBone.MWEIGHTS),nmNumWeights(struct));
}
