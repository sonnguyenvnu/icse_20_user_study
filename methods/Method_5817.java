/** 
 * Starts loading a  {@link Loadable}. <p>The calling thread must be a  {@link Looper} thread, which is the thread on which the {@link Callback} will be called.
 * @param < T > The type of the loadable.
 * @param loadable The {@link Loadable} to load.
 * @param callback A callback to be called when the load ends.
 * @param defaultMinRetryCount The minimum number of times the load must be retried before {@link #maybeThrowError()} will propagate an error.
 * @throws IllegalStateException If the calling thread does not have an associated {@link Looper}.
 * @return {@link SystemClock#elapsedRealtime} when the load started.
 */
public <T extends Loadable>long startLoading(T loadable,Callback<T> callback,int defaultMinRetryCount){
  Looper looper=Looper.myLooper();
  Assertions.checkState(looper != null);
  fatalError=null;
  long startTimeMs=SystemClock.elapsedRealtime();
  new LoadTask<>(looper,loadable,callback,defaultMinRetryCount,startTimeMs).start(0);
  return startTimeMs;
}
