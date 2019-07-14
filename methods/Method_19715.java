@ExceptionHandler(UserNotExistException.class) @ResponseBody @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public Map<String,Object> handleUserNotExistsException(UserNotExistException e){
  Map<String,Object> map=new HashMap<>();
  map.put("id",e.getId());
  map.put("message",e.getMessage());
  return map;
}
