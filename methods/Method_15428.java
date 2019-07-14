@Override public JSONObject parseResponse(JSONObject request){
  if (session != null && request != null && request.get(JSONRequest.KEY_FORMAT) == null) {
    request.put(JSONRequest.KEY_FORMAT,session.getAttribute(JSONRequest.KEY_FORMAT));
  }
  return super.parseResponse(request);
}
