/** 
 * This method is intended to be called only from `KSAdapter` in order for it to inform the view holder of its lifecycle.
 */
public void lifecycleEvent(final @NonNull ActivityEvent event){
  this.lifecycle.onNext(event);
  if (ActivityEvent.DESTROY.equals(event)) {
    destroy();
  }
}
