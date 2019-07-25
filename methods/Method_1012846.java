/** 
 * ????
 * @return
 */
public static String failed(){
  return toString(new ResultBody().setCode(ResultEnum.FAIL.getCode()).setMessage(ResultEnum.FAIL.getMessage()));
}
