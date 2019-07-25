/** 
 * A constant  {@link HashOrderMixingStrategy}. This is useful if one needs to have deterministic key distribution but wishes to control it manually. Do not use the same constant for more than one container. Consider using  {@linkplain ObjectScatterSet scatter maps or sets} insteadof constant hash order mixer.
 */
public static HashOrderMixingStrategy constant(final long seed){
  return new HashOrderMixingStrategy(){
    @Override public int newKeyMixer(    int newContainerBufferSize){
      return (int)BitMixer.mix64(newContainerBufferSize ^ seed);
    }
    @Override public HashOrderMixingStrategy clone(){
      return this;
    }
  }
;
}
