public static String failed(ResultEnum resultEnum){
  return toString(new ResultBody().setCode(resultEnum.getCode()).setMessage(resultEnum.getMessage()));
}
