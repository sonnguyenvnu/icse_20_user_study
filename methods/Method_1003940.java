/** 
 * Exports an  {@link AtomicInteger}, which will be included in time series tracking.
 * @param name The name to export the stat with.
 * @param intVar The variable to export.
 * @return A reference to the {@link AtomicInteger} provided.
 */
public static AtomicInteger export(final String name,final AtomicInteger intVar){
  export(new SampledStat<Integer>(name,0){
    @Override public Integer doSample(){
      return intVar.get();
    }
  }
);
  return intVar;
}
