/** 
 * Deterministic  {@link HashOrderMixingStrategy} will reorder keys dependingon the size of the container's buffer. This is inherently unsafe with hash containers using linear conflict addressing. The only use case when this can be useful is to count/ collect unique keys (for which scatter tables should be used).
 * @deprecated Permanently deprecated as a warning signal.
 */
@Deprecated public static HashOrderMixingStrategy deterministic(){
  return DETERMINISTIC;
}
