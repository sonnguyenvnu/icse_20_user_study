@ExceptionHandler(ConstraintViolationException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) ResponseMessage handleConstraintViolationException(ConstraintViolationException e){
  SimpleValidateResults results=new SimpleValidateResults();
  for (  ConstraintViolation<?> violation : e.getConstraintViolations()) {
    results.addResult(violation.getPropertyPath().toString(),violation.getMessage());
  }
  List<ValidateResults.Result> errorResults=results.getResults();
  return ResponseMessage.error(400,errorResults.isEmpty() ? "" : errorResults.get(0).getMessage()).result(errorResults);
}
