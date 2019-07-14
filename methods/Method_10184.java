/** 
 * Constructs a failed result.
 * @return result
 */
public static JSONObject newFail(){
  return new JSONObject().put(Keys.CODE,StatusCodes.ERR).put(Keys.MSG,"System is abnormal, please try again later");
}
