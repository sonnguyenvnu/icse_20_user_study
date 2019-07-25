/** 
 * Add an observer of model changes to this loop. If  {@link #getMostRecentModel()} is non-null,the observer will immediately be notified of the most recent model. The observer will be notified of future changes to the model until the loop or the returned  {@link Disposable} isdisposed.
 * @param observer a non-null observer of model changes
 * @return a {@link Disposable} that can be used to stop further notifications to the observer
 * @throws NullPointerException if the observer is null
 * @throws IllegalStateException if the loop has been disposed
 */
public Disposable observe(final Consumer<M> observer){
  if (disposed)   throw new IllegalStateException("This loop has already been disposed. You cannot observe a disposed loop");
  modelObservers.add(checkNotNull(observer));
  final M currentModel=mostRecentModel;
  if (currentModel != null) {
    observer.accept(currentModel);
  }
  return new Disposable(){
    @Override public void dispose(){
      modelObservers.remove(observer);
    }
  }
;
}
