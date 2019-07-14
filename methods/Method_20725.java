/** 
 * Zips two observables up into an observable of pairs.
 */
public static <S,T>ZipPairTransformer<S,T> zipPair(final @NonNull Observable<T> second){
  return new ZipPairTransformer<>(second);
}
