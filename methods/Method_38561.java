/** 
 * Prepares the JSON payload that carries on the token value.
 */
protected JsonResult tokenAsJson(final T authToken){
  final JsonObject jsonObject=new JsonObject();
  jsonObject.put("token",userAuth.tokenValue(authToken));
  return JsonResult.of(jsonObject);
}
