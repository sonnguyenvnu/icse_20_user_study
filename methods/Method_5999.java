/** 
 * Creates a  {@link Handler} with the specified {@link Handler.Callback} on the specified {@link Looper} thread. The method accepts partially initialized objects as callback under theassumption that the Handler won't be used to send messages until the callback is fully initialized.
 * @param looper A {@link Looper} to run the callback on.
 * @param callback A {@link Handler.Callback}. May be a partially initialized class.
 * @return A {@link Handler} with the specified callback on the current {@link Looper} thread.
 */
@SuppressWarnings({"nullness:argument.type.incompatible","nullness:return.type.incompatible"}) public static Handler createHandler(Looper looper,Handler.@UnknownInitialization Callback callback){
  return new Handler(looper,callback);
}
