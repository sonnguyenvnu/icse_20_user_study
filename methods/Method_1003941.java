/** 
 * Exports an  {@link AtomicLong}, which will be included in time series tracking.
 * @param name The name to export the stat with.
 * @param longVar The variable to export.
 * @return A reference to the {@link AtomicLong} provided.
 */
public static AtomicLong export(String name,final AtomicLong longVar){
  export(new StatImpl<Long>(name){
    @Override public Long read(){
      return longVar.get();
    }
  }
);
  return longVar;
}
