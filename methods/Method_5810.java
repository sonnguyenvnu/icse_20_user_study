/** 
 * Blacklists resources whose load error was an  {@link InvalidResponseCodeException} with responsecode HTTP 404 or 410. The duration of the blacklisting is  {@link #DEFAULT_TRACK_BLACKLIST_MS}.
 */
@Override public long getBlacklistDurationMsFor(int dataType,long loadDurationMs,IOException exception,int errorCount){
  if (exception instanceof InvalidResponseCodeException) {
    int responseCode=((InvalidResponseCodeException)exception).responseCode;
    return responseCode == 404 || responseCode == 410 ? DEFAULT_TRACK_BLACKLIST_MS : C.TIME_UNSET;
  }
  return C.TIME_UNSET;
}
