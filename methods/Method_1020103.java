/** 
 * ???? 
 */
public static Result error(Integer code,String msg){
  Result result=new Result();
  result.setRespCode(code);
  result.setRespMsg(msg);
  return result;
}
