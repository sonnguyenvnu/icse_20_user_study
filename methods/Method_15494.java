/** 
 * @return response
 * @throws Exception
 */
@Override public JSONObject response() throws Exception {
  if (sqlReponse == null || sqlReponse.isEmpty()) {
    if (isTable) {
      return response;
    }
  }
 else {
    response.putAll(sqlReponse);
  }
  if (corrected != null) {
    response.put(KEY_CORRECT,corrected);
  }
  if (customMap != null) {
    response.putAll(customMap);
  }
  onFunctionResponse("0");
  onChildResponse();
  onFunctionResponse("+");
  onComplete();
  return response;
}
