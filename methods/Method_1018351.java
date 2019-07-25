@ExceptionHandler public ResponseEntity<Void> handle(HttpRequestMethodNotSupportedException exception){
  return exception.toResponse();
}
