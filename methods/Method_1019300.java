/** 
 * This strategy does not change the hash order of keys at all. This is inherently unsafe with hash containers using linear conflict  addressing. The only use case when this can be useful is to count/ collect unique keys (for which scatter tables should be used).
 * @deprecated Permanently deprecated as a warning signal.
 */
@Deprecated public static HashOrderMixingStrategy none(){
  return new HashOrderMixingStrategy(){
    @Override public int newKeyMixer(    int newContainerBufferSize){
      return 0;
    }
    @Override public HashOrderMixingStrategy clone(){
      return this;
    }
  }
;
}
