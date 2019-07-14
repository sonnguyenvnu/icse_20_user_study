/** 
 * Emits the latest values from two observables whenever either emits.
 */
public static <S,T>CombineLatestPairTransformer<S,T> combineLatestPair(final @NonNull Observable<T> second){
  return new CombineLatestPairTransformer<>(second);
}
