/** 
 * Create a new watch.
 * @return a {@link com.spotify.heroic.metric.QueryTrace.Watch}
 * @deprecated use {@link Tracing#watch()}
 */
static Watch watch(){
  return ActiveWatch.create(Stopwatch.createStarted());
}
