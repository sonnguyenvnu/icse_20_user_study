/** 
 * Converts an  {@link ApolloStoreOperation} to a Single.
 * @param operation the ApolloStoreOperation to convert
 * @param < T >       the value type
 * @return the converted Single
 */
@NotNull public static <T>Single<T> from(@NotNull final ApolloStoreOperation<T> operation){
  checkNotNull(operation,"operation == null");
  return Single.create(new Single.OnSubscribe<T>(){
    @Override public void call(    final SingleSubscriber<? super T> subscriber){
      operation.enqueue(new ApolloStoreOperation.Callback<T>(){
        @Override public void onSuccess(        T result){
          subscriber.onSuccess(result);
        }
        @Override public void onFailure(        Throwable t){
          subscriber.onError(t);
        }
      }
);
    }
  }
);
}
