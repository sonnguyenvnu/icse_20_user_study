/** 
 * Convert a status code returned by  {@link ThreadReference#status() }to a human readable form.
 * @param status {@link ThreadReference#THREAD_STATUS_MONITOR}, {@link ThreadReference#THREAD_STATUS_NOT_STARTED}, {@link ThreadReference#THREAD_STATUS_RUNNING}, {@link ThreadReference#THREAD_STATUS_SLEEPING}, {@link ThreadReference#THREAD_STATUS_UNKNOWN}, {@link ThreadReference#THREAD_STATUS_WAIT} or{@link ThreadReference#THREAD_STATUS_ZOMBIE}
 * @return String containing readable status code.
 */
protected String threadStatusToString(int status){
switch (status) {
case ThreadReference.THREAD_STATUS_MONITOR:
    return "THREAD_STATUS_MONITOR";
case ThreadReference.THREAD_STATUS_NOT_STARTED:
  return "THREAD_STATUS_NOT_STARTED";
case ThreadReference.THREAD_STATUS_RUNNING:
return "THREAD_STATUS_RUNNING";
case ThreadReference.THREAD_STATUS_SLEEPING:
return "THREAD_STATUS_SLEEPING";
case ThreadReference.THREAD_STATUS_UNKNOWN:
return "THREAD_STATUS_UNKNOWN";
case ThreadReference.THREAD_STATUS_WAIT:
return "THREAD_STATUS_WAIT";
case ThreadReference.THREAD_STATUS_ZOMBIE:
return "THREAD_STATUS_ZOMBIE";
default :
return "";
}
}
