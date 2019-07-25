/** 
 * Create an  {@link uk.gov.gchq.gaffer.core.exception.Error} object from a{@link uk.gov.gchq.gaffer.core.exception.GafferWrappedErrorRuntimeException}.
 * @param gex the exception object
 * @return the error from within the exception
 */
public static Error from(final GafferWrappedErrorRuntimeException gex){
  LOGGER.error("Error: {}",gex.getError().getSimpleMessage(),gex);
  return gex.getError();
}
