/** 
 * Adds the current non-null OnSchedule hook to this handler and replaces it in RxJavaPlugins with this OnScheduleMultiHandlerManager.
 */
public void append(){
  @SuppressWarnings("unchecked") Function<Runnable,Runnable> existing=(Function<Runnable,Runnable>)RxJavaPlugins.getScheduleHandler();
  if (existing != this) {
    if (existing != null) {
      register(existing);
    }
    RxJavaPlugins.setScheduleHandler(this);
  }
}
