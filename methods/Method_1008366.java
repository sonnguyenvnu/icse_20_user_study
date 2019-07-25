@SuppressLoggerChecks(reason="safely delegates to logger") void deprecated(final Set<ThreadContext> threadContexts,final String message,final boolean log,final Object... params){
  final Iterator<ThreadContext> iterator=threadContexts.iterator();
  if (iterator.hasNext()) {
    final String formattedMessage=LoggerMessageFormat.format(message,params);
    final String warningHeaderValue=formatWarning(formattedMessage);
    assert WARNING_HEADER_PATTERN.matcher(warningHeaderValue).matches();
    assert extractWarningValueFromWarningHeader(warningHeaderValue).equals(escapeAndEncode(formattedMessage));
    while (iterator.hasNext()) {
      try {
        final ThreadContext next=iterator.next();
        next.addResponseHeader("Warning",warningHeaderValue,DeprecationLogger::extractWarningValueFromWarningHeader);
      }
 catch (      final IllegalStateException e) {
      }
    }
  }
  if (log) {
    logger.warn(message,params);
  }
}
