/** 
 * Completes an observable when an  {@link FragmentEvent} occurs in the fragment's lifecycle.
 */
@Override public final @NonNull <T>Observable.Transformer<T,T> bindUntilEvent(final @NonNull FragmentEvent event){
  return RxLifecycle.bindUntilFragmentEvent(this.lifecycle,event);
}
