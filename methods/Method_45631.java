/** 
 * ?????????????RuntimeException/Error? ??Cause????????????cause??????????.
 */
public void cutCause(Throwable bizException){
  if (causeField == null) {
    return;
  }
  Throwable rootCause=bizException;
  while (null != rootCause.getCause()) {
    rootCause=rootCause.getCause();
  }
  if (rootCause != bizException) {
    bizException.setStackTrace(rootCause.getStackTrace());
    try {
      causeField.set(bizException,bizException);
    }
 catch (    Exception e) {
      LOGGER.warnWithApp(null,LogCodes.getLog(LogCodes.WARN_PROVIDER_CUT_CAUSE),e);
    }
  }
}
