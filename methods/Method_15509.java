/** 
 * ???????????
 * @param object
 * @return
 */
public static JSONObject extendErrorResult(JSONObject object,Exception e){
  JSONObject error=newErrorResult(e);
  return extendResult(object,error.getIntValue(JSONResponse.KEY_CODE),error.getString(JSONResponse.KEY_MSG));
}
