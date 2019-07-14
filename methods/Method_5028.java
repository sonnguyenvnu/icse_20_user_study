/** 
 * Returns the failure string for the given failure reason value. 
 */
public static String getFailureString(@FailureReason int failureReason){
switch (failureReason) {
case FAILURE_REASON_NONE:
    return "NO_REASON";
case FAILURE_REASON_UNKNOWN:
  return "UNKNOWN_REASON";
default :
throw new IllegalStateException();
}
}
