/** 
 * Returns an access log writer with the specified  {@code formatStr}.
 */
static AccessLogWriter custom(String formatStr){
  requireNonNull(formatStr,"formatStr");
  final List<AccessLogComponent> accessLogFormat=parseCustom(formatStr);
  checkArgument(!accessLogFormat.isEmpty(),"Invalid access log format string: %s",formatStr);
  return requestLog -> AccessLogger.write(accessLogFormat,requestLog);
}
