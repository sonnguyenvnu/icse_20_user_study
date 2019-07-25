/** 
 * Returns a  {@link CancelledSubscriptionException} which may be a singleton or a new instance, dependingon whether  {@linkplain Flags#verboseExceptions() the verbose exception mode} is enabled.
 */
public static CancelledSubscriptionException get(){
  return Flags.verboseExceptions() ? new CancelledSubscriptionException() : INSTANCE;
}
