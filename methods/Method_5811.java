/** 
 * Retries for any exception that is not a subclass of  {@link ParserException} or {@link FileNotFoundException}. The retry delay is calculated as  {@code Math.min((errorCount - 1) 1000, 5000)}.
 */
@Override public long getRetryDelayMsFor(int dataType,long loadDurationMs,IOException exception,int errorCount){
  return exception instanceof ParserException || exception instanceof FileNotFoundException ? C.TIME_UNSET : Math.min((errorCount - 1) * 1000,5000);
}
