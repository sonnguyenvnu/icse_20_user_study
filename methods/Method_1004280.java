/** 
 * Wraps  {@code callback} to be be operated on specified {@code handler}
 * @param callback original callback to delegates calls
 * @param handler  the callback will be run on the thread to which this handler is attached
 */
public static <T>ApolloCallback<T> wrap(@NotNull ApolloCall.Callback<T> callback,@NotNull Handler handler){
  return new ApolloCallback<>(callback,handler);
}
