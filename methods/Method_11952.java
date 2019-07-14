/** 
 * Creates a  {@link Timeout} that will timeout a test after thegiven duration, in seconds.
 * @since 4.12
 */
public static Timeout seconds(long seconds){
  return new Timeout(seconds,TimeUnit.SECONDS);
}
