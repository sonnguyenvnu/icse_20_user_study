@ExceptionHandler(BindException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) ResponseMessage handleException(BindException e){
  SimpleValidateResults results=new SimpleValidateResults();
  e.getBindingResult().getAllErrors().stream().filter(FieldError.class::isInstance).map(FieldError.class::cast).forEach(fieldError -> results.addResult(fieldError.getField(),fieldError.getDefaultMessage()));
  return ResponseMessage.error(400,results.getResults().isEmpty() ? e.getMessage() : results.getResults().get(0).getMessage()).result(results.getResults());
}
