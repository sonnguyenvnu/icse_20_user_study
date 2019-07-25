/** 
 * Creates a  {@link Subscriber} that expects a single value response.
 * @param subscriber callback for the response
 * @param < T >        type of element signaled
 * @return the subscriber
 */
@Nonnull public static <T>CancellableSubscriber<T> single(@Nonnull final SingleSubscriber<T> subscriber){
  final AtomicBoolean hasResponse=new AtomicBoolean();
  return new CancellableSubscriber<T>(){
    @Override public void cancel(){
      if (subscription != null) {
        hasResponse.set(true);
        subscription.cancel();
        subscription=null;
      }
    }
    @Override public final void onComplete(){
      onError(new NoSuchElementException());
    }
    @Override public void onError(    @Nonnull final Throwable t){
      if (hasResponse.compareAndSet(false,true)) {
        subscriber.onError(t);
      }
    }
    @Override public final void onNext(    final T t){
      if (hasResponse.compareAndSet(false,true)) {
        cancel();
        subscriber.onSuccess(t);
      }
    }
    @Override public final void onSubscribe(    @Nonnull final Subscription subscription){
      this.subscription=subscription;
      subscription.request(1);
    }
  }
;
}
