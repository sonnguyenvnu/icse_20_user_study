public static ServiceException exception(Integer code,String messagePattern,Object... params){
  String message=doFormat(code,messagePattern,params);
  return new ServiceException(code,message);
}
