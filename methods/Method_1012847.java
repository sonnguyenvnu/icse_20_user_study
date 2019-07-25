/** 
 * ????????????
 * @param message ????
 * @return
 */
public static String failed(String message){
  return toString(new ResultBody().setCode(ResultEnum.FAIL.getCode()).setMessage(message));
}
