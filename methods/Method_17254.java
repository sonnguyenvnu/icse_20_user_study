/** 
 * Returns a weigher that enforces that the weight is non-negative.
 * @param delegate the weigher to weighs the entry
 * @param < K > the type of keys
 * @param < V > the type of values
 * @return a weigher that enforces that the weight is non-negative
 */
@NonNull static <K,V>Weigher<K,V> boundedWeigher(@NonNull Weigher<K,V> delegate){
  return new BoundedWeigher<>(delegate);
}
