/** 
 * Returns if the sketch has not yet been initialized, requiring that  {@link #ensureCapacity} iscalled before it begins to track frequencies.
 */
public boolean isNotInitialized(){
  return (table == null);
}
