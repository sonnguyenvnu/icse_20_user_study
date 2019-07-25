/** 
 * Returns an access log writer with a combined format.
 */
static AccessLogWriter combined(){
  return requestLog -> AccessLogger.write(AccessLogFormats.COMBINED,requestLog);
}
