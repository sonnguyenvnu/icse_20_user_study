/** 
 * Emits the number of times the source has emitted for every emission of the source. The first emitted value will be `1`.
 */
public static @NonNull <T>IncrementalCountTransformer<T> incrementalCount(){
  return new IncrementalCountTransformer<>();
}
