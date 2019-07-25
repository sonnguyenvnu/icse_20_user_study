/** 
 * Converts an  {@link ApolloQueryWatcher} into an Observable. Honors the back pressure from downstream with the backpressure strategy  {@link rx.Emitter.BackpressureMode#LATEST}.
 * @param watcher the ApolloQueryWatcher to convert
 * @param < T >     the value type
 * @return the converted Observable
 */
@NotNull public static <T>Observable<Response<T>> from(@NotNull final ApolloQueryWatcher<T> watcher){
  return from(watcher,Emitter.BackpressureMode.LATEST);
}
