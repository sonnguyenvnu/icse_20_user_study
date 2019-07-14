/** 
 * @return this rate limit as a number of milliseconds required between any two remote calls,assuming the client makes consecutive calls without any bursts or breaks for an infinite period of time.
 */
@JsonIgnore public long getPollDelayMillis(){
  return timeUnit.toMillis(timeSpan) / calls;
}
