/** 
 * Determines from a view and lifecycle event if the view's life is over.
 */
private boolean isFinished(final @NonNull ViewType view,final @NonNull ActivityEvent event){
  if (view instanceof BaseActivity) {
    return event == ActivityEvent.DESTROY && ((BaseActivity)view).isFinishing();
  }
  return event == ActivityEvent.DESTROY;
}
