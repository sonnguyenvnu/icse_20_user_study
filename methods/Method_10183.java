/** 
 * Constructs a successful result.
 * @return result
 */
public static JSONObject newSucc(){
  return new JSONObject().put(Keys.CODE,StatusCodes.SUCC).put(Keys.MSG,"");
}
