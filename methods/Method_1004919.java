/** 
 * Create an  {@link uk.gov.gchq.gaffer.core.exception.Error} object from an{@link UnauthorisedException}.
 * @param e the exception object
 * @return a newly constructed {@link uk.gov.gchq.gaffer.core.exception.Error}
 */
public static Error from(final UnauthorisedException e){
  LOGGER.error("Error: {}",e.getMessage(),e);
  return new ErrorBuilder().status(Status.FORBIDDEN).simpleMessage(e.getMessage()).detailMessage(ExceptionUtils.getStackTrace(e)).build();
}
