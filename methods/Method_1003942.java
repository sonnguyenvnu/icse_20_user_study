/** 
 * Exports an  {@link AtomicDouble}, which will be included in time series tracking.
 * @param name The name to export the stat with.
 * @param doubleVar The variable to export.
 * @return A reference to the {@link AtomicDouble} provided.
 */
public static AtomicDouble export(String name,final AtomicDouble doubleVar){
  export(new StatImpl<Double>(name){
    @Override public Double read(){
      return doubleVar.doubleValue();
    }
  }
);
  return doubleVar;
}
