/** 
 * Unsafe version of  {@link #mWeights(AIVertexWeight.Buffer) mWeights}. 
 */
public static void nmWeights(long struct,AIVertexWeight.Buffer value){
  memPutAddress(struct + AIBone.MWEIGHTS,value.address());
  nmNumWeights(struct,value.remaining());
}
