/** 
 * Returns an access log writer with a common format.
 */
static AccessLogWriter common(){
  return requestLog -> AccessLogger.write(AccessLogFormats.COMMON,requestLog);
}
