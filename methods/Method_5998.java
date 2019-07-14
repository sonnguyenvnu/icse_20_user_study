/** 
 * Creates a  {@link Handler} with the specified {@link Handler.Callback} on the current {@link Looper} thread. The method accepts partially initialized objects as callback under theassumption that the Handler won't be used to send messages until the callback is fully initialized. <p>If the current thread doesn't have a  {@link Looper}, the application's main thread  {@link Looper} is used.
 * @param callback A {@link Handler.Callback}. May be a partially initialized class.
 * @return A {@link Handler} with the specified callback on the current {@link Looper} thread.
 */
public static Handler createHandler(Handler.@UnknownInitialization Callback callback){
  return createHandler(getLooper(),callback);
}
