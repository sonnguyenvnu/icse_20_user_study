/** 
 * Persists the cleansed rows with the specified storage level.
 */
public void persist(@Nonnull final StorageLevel newLevel){
  cleansedRowResultRDD.persist(newLevel);
}
