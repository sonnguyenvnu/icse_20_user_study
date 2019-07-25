@ExceptionHandler(RedisKeeperRuntimeException.class) public ResponseEntity<Object> exception(HttpServletRequest request,RedisKeeperRuntimeException ex){
  Map<String,String> headers=ImmutableMap.of(KeeperContainerErrorParser.ERROR_HEADER_NAME,ex.getErrorMessage().getErrorType().toString());
  return handleError(request,INTERNAL_SERVER_ERROR,ex,headers);
}
