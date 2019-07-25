public static String error(){
  return toString(new ResultBody().setCode(ResultEnum.ERROR.getCode()).setMessage(ResultEnum.ERROR.getMessage()));
}
