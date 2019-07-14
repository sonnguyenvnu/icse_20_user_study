/** 
 * ???????????
 * @param object
 * @return
 */
public static JSONObject extendSuccessResult(JSONObject object){
  return extendResult(object,JSONResponse.CODE_SUCCESS,JSONResponse.MSG_SUCCEED);
}
