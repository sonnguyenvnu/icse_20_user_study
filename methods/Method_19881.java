/** 
 * ??????????(????)
 * @param e ConstraintViolationException
 * @return FebsResponse
 */
@ExceptionHandler(value=ConstraintViolationException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) public Response handleConstraintViolationException(ConstraintViolationException e){
  StringBuilder message=new StringBuilder();
  Set<ConstraintViolation<?>> violations=e.getConstraintViolations();
  for (  ConstraintViolation<?> violation : violations) {
    Path path=violation.getPropertyPath();
    String[] pathArr=StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(),".");
    message.append(pathArr[1]).append(violation.getMessage()).append(",");
  }
  message=new StringBuilder(message.substring(0,message.length() - 1));
  return new Response().message(message.toString());
}
