/** 
 * Create an  {@link uk.gov.gchq.gaffer.core.exception.Error} object from a{@link uk.gov.gchq.gaffer.core.exception.GafferRuntimeException}.
 * @param gex the exception object
 * @return a newly constructed {@link uk.gov.gchq.gaffer.core.exception.Error}
 */
public static Error from(final GafferRuntimeException gex){
  LOGGER.error("Error: {}",gex.getMessage(),gex);
  return new ErrorBuilder().status(gex.getStatus()).simpleMessage(gex.getMessage()).detailMessage(ExceptionUtils.getStackTrace(gex)).build();
}
