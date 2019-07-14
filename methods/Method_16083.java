/** 
 * ? {@link this#isWriteJSONObjectEnabled()}??true?,?????json???,???????????
 * @return ???????
 * @see this#isWriteJSONObjectEnabled()
 */
default Object getWriteJSONObject(){
  JSONObject jsonObject=new JSONObject();
  jsonObject.put("value",getValue());
  jsonObject.put("text",getText());
  jsonObject.put("index",index());
  jsonObject.put("mask",getMask());
  return jsonObject;
}
