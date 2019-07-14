/** 
 * Converts an expiration duration/unit pair into a single long for hashing and equality. 
 */
static long durationInNanos(long duration,@Nullable TimeUnit unit){
  return (unit == null) ? UNSET_INT : unit.toNanos(duration);
}
