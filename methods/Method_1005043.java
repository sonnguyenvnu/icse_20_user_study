/** 
 * Create an  {@link uk.gov.gchq.gaffer.core.exception.Error} object from a{@link javax.ws.rs.WebApplicationException}.
 * @param ex the exception object
 * @return a newly constructed {@link uk.gov.gchq.gaffer.core.exception.Error}
 */
public static Error from(final WebApplicationException ex){
  return new ErrorBuilder().statusCode(ex.getResponse().getStatus()).simpleMessage(ex.getMessage()).detailMessage(ExceptionUtils.getStackTrace(ex)).build();
}
