/** 
 * Creates a  {@link LoadErrorAction} for retrying with the given parameters.
 * @param resetErrorCount Whether the previous error count should be set to zero.
 * @param retryDelayMillis The number of milliseconds to wait before retrying.
 * @return A {@link LoadErrorAction} for retrying with the given parameters.
 */
public static LoadErrorAction createRetryAction(boolean resetErrorCount,long retryDelayMillis){
  return new LoadErrorAction(resetErrorCount ? ACTION_TYPE_RETRY_AND_RESET_ERROR_COUNT : ACTION_TYPE_RETRY,retryDelayMillis);
}
