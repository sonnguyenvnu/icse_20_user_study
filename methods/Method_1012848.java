/** 
 * ???????????? + ???
 * @param code ???
 * @param msg  ??
 * @return
 */
public static String failed(Integer code,String msg){
  return toString(new ResultBody().setCode(code).setMessage(msg));
}
