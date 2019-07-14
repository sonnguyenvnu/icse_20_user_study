/** 
 * ??JSONObject????????????????
 * @param object
 * @param code
 * @param msg
 * @return
 */
public static JSONObject extendResult(JSONObject object,int code,String msg){
  if (object == null) {
    object=new JSONObject(true);
  }
  if (object.containsKey(JSONResponse.KEY_CODE) == false) {
    object.put(JSONResponse.KEY_CODE,code);
  }
  String m=StringUtil.getString(object.getString(JSONResponse.KEY_MSG));
  if (m.isEmpty() == false) {
    msg=m + " ;\n " + StringUtil.getString(msg);
  }
  object.put(JSONResponse.KEY_MSG,msg);
  return object;
}
