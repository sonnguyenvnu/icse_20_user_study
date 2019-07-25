/** 
 * Adds the given observer to the observers list within the lifespan of the given owner. The events are dispatched on the main thread. If LiveData already has data set, it will be delivered to the observer. <p> The observer will only receive events if the owner is in  {@link Lifecycle.State#STARTED}or  {@link Lifecycle.State#RESUMED} state (active).<p> If the owner moves to the  {@link Lifecycle.State#DESTROYED} state, the observer willautomatically be removed. <p> When data changes while the  {@code owner} is not active, it will not receive any updates.If it becomes active again, it will receive the last available data automatically. <p> LiveData keeps a strong reference to the observer and the owner as long as the given LifecycleOwner is not destroyed. When it is destroyed, LiveData removes references to the observer &amp; the owner. <p> If the given owner is already in  {@link Lifecycle.State#DESTROYED} state, LiveDataignores the call. <p> If the given owner, observer tuple is already in the list, the call is ignored. If the observer is already in the list with another owner, LiveData throws an {@link IllegalArgumentException}.
 * @param owner    The LifecycleOwner which controls the observer
 * @param observer The observer that will receive the events
 */
@MainThread public void observe(@NonNull LifecycleOwner owner,@NonNull Observer<T> observer){
  if (owner.getLifecycle().getCurrentState() == DESTROYED) {
    return;
  }
  LifecycleBoundObserver wrapper=new LifecycleBoundObserver(owner,observer);
  wrapper.mLastVersion=getVersion();
  ObserverWrapper existing=mObservers.putIfAbsent(observer,wrapper);
  if (existing != null && !existing.isAttachedTo(owner)) {
    throw new IllegalArgumentException("Cannot add the same observer" + " with different lifecycles");
  }
  if (existing != null) {
    return;
  }
  owner.getLifecycle().addObserver(wrapper);
}
