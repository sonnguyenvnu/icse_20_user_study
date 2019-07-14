/** 
 * Throws a decode exception, if there is one.
 * @throws E The decode exception.
 */
private void maybeThrowException() throws E {
  if (exception != null) {
    throw exception;
  }
}
