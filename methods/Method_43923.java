/** 
 * @return minimum number of milliseconds required between any two remote calls, assuming theclient makes consecutive calls without any bursts or breaks for an infinite period of time. Returns null if the rateLimits collection is null or empty
 */
@JsonIgnore public static Long getPollDelayMillis(RateLimit[] rateLimits){
  if (rateLimits == null || rateLimits.length == 0) {
    return null;
  }
  long result=0;
  for (  RateLimit rateLimit : rateLimits) {
    result=Math.max(result,rateLimit.getPollDelayMillis());
  }
  return result;
}
