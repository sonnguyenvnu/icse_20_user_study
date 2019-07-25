/** 
 * Handles  {@link ETagDoesntMatchException} by returning {@code 412 Precondition Failed}.
 * @param o_O the exception to handle.
 * @return
 */
@ExceptionHandler ResponseEntity<Void> handle(ETagDoesntMatchException o_O){
  HttpHeaders headers=o_O.getExpectedETag().addTo(new HttpHeaders());
  return response(HttpStatus.PRECONDITION_FAILED,headers);
}
