/** 
 * Check the given exception for the resource that needs authorization. If the exception was not thrown because a resource needed authorization, then rethrow the exception.
 * @param ex The exception.
 * @return The resource that needed authorization (never null).
 * @throws ServletException in the case of an underlying Servlet API exception
 * @throws IOException in the case of general IO exceptions
 */
protected ProtectedResourceDetails checkForResourceThatNeedsAuthorization(Exception ex) throws ServletException, IOException {
  Throwable[] causeChain=getThrowableAnalyzer().determineCauseChain(ex);
  AccessTokenRequiredException ase=(AccessTokenRequiredException)getThrowableAnalyzer().getFirstThrowableOfType(AccessTokenRequiredException.class,causeChain);
  ProtectedResourceDetails resourceThatNeedsAuthorization;
  if (ase != null) {
    resourceThatNeedsAuthorization=ase.getResource();
    if (resourceThatNeedsAuthorization == null) {
      throw new OAuthRequestFailedException(ase.getMessage());
    }
  }
 else {
    if (ex instanceof ServletException) {
      throw (ServletException)ex;
    }
    if (ex instanceof IOException) {
      throw (IOException)ex;
    }
 else     if (ex instanceof RuntimeException) {
      throw (RuntimeException)ex;
    }
    throw new RuntimeException(ex);
  }
  return resourceThatNeedsAuthorization;
}
