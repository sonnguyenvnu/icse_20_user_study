/** 
 * ??????????(??????)
 * @param e BindException
 * @return FebsResponse
 */
@ExceptionHandler(BindException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) public Response validExceptionHandler(BindException e){
  StringBuilder message=new StringBuilder();
  List<FieldError> fieldErrors=e.getBindingResult().getFieldErrors();
  for (  FieldError error : fieldErrors) {
    message.append(error.getField()).append(error.getDefaultMessage()).append(",");
  }
  message=new StringBuilder(message.substring(0,message.length() - 1));
  return new Response().message(message.toString());
}
