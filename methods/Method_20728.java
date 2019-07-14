/** 
 * If called on the main thread, schedule the work immediately. Otherwise delay execution of the work by adding it to a message queue, where it will be executed on the main thread. This is particularly useful for RecyclerViews; if subscriptions in these views are delayed for a frame, then the view temporarily shows recycled content and frame rate stutters. To address that, we can use `observeForUI()` to execute the work immediately rather than wait for a frame.
 */
public static @NonNull <T>ObserveForUITransformer<T> observeForUI(){
  return new ObserveForUITransformer<>();
}
