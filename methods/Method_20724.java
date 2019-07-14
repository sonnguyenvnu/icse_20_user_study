/** 
 * Emits the latest value of the source `when` observable whenever the `when` observable emits.
 */
public static <S,T>TakePairWhenTransformer<S,T> takePairWhen(final @NonNull Observable<T> when){
  return new TakePairWhenTransformer<>(when);
}
