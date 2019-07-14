/** 
 * Checks whether the provided status includes the `IS_LAST` flag, marking this as the last result the consumer will receive.
 */
public static boolean isNotLast(@Consumer.Status int status){
  return !isLast(status);
}
