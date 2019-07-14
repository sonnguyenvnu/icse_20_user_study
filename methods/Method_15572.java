/** 
 * ??????
 * @param table
 * @param key
 * @param value
 * @param exceptId ???id
 * @throws Exception
 */
@Override public void verifyRepeat(String table,String key,Object value,long exceptId) throws Exception {
  if (key == null || value == null) {
    Log.e(TAG,"verifyRepeat  key == null || value == null >> return;");
    return;
  }
  if (value instanceof JSON) {
    throw new UnsupportedDataTypeException(key + ":value ?value??????JSON?");
  }
  JSONRequest request=new JSONRequest(key,value);
  if (exceptId > 0) {
    request.put(JSONRequest.KEY_ID + "!",exceptId);
  }
  JSONObject repeat=createParser().setMethod(HEAD).setNoVerify(true).parseResponse(new JSONRequest(table,request));
  repeat=repeat == null ? null : repeat.getJSONObject(table);
  if (repeat == null) {
    throw new Exception("???????  verifyRepeat  repeat == null");
  }
  if (repeat.getIntValue(JSONResponse.KEY_COUNT) > 0) {
    throw new ConflictException(key + ": " + value + " ??????????");
  }
}
