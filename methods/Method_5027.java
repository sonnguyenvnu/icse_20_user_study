/** 
 * Returns the state string for the given state value. 
 */
public static String getStateString(@State int state){
switch (state) {
case STATE_QUEUED:
    return "QUEUED";
case STATE_STOPPED:
  return "STOPPED";
case STATE_DOWNLOADING:
return "DOWNLOADING";
case STATE_COMPLETED:
return "COMPLETED";
case STATE_FAILED:
return "FAILED";
case STATE_REMOVING:
return "REMOVING";
case STATE_REMOVED:
return "REMOVED";
case STATE_RESTARTING:
return "RESTARTING";
default :
throw new IllegalStateException();
}
}
