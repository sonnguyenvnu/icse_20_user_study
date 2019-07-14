/** 
 * Rejects the timestamp last polled in  {@link #maybePollTimestamp(long)}. The instance will enter the error state and poll timestamps infrequently until the next call to  {@link #acceptTimestamp()}.
 */
public void rejectTimestamp(){
  updateState(STATE_ERROR);
}
