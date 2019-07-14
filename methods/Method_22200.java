/** 
 * Sets the Thread on which an uncaught Exception occurred.
 * @param thread Thread on which an uncaught Exception occurred.
 * @return the updated {@code ReportBuilder}
 */
@NonNull public ReportBuilder uncaughtExceptionThread(@Nullable Thread thread){
  uncaughtExceptionThread=thread;
  return this;
}
