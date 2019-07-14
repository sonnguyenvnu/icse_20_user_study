/** 
 * Convert from HystrixEventType to HystrixRollingNumberEvent
 * @param eventType HystrixEventType
 * @return HystrixRollingNumberEvent
 * @deprecated Instead, use {@link HystrixRollingNumberEvent#from(HystrixEventType)}
 */
@Deprecated protected final HystrixRollingNumberEvent getRollingNumberTypeFromEventType(HystrixEventType eventType){
  return HystrixRollingNumberEvent.from(eventType);
}
