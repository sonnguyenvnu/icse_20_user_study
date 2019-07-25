/** 
 * Writes an access log for the specified  {@link RequestLog}.
 */
static void write(List<AccessLogComponent> format,RequestLog log){
  final VirtualHost host=((ServiceRequestContext)log.context()).virtualHost();
  final Logger logger=host.accessLogger();
  if (!format.isEmpty() && logger.isInfoEnabled()) {
    logger.info(format(format,log));
  }
}
