public static <T>CommonResult<T> error(Integer code,String message){
  Assert.isTrue(!CODE_SUCCESS.equals(code),"code ???????");
  CommonResult<T> result=new CommonResult<>();
  result.code=code;
  result.message=message;
  return result;
}
