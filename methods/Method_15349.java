@Override public JSONObject parseResponse(JSONObject request){
  if (session != null && request != null) {
    if (request.get(JSONRequest.KEY_FORMAT) == null) {
      request.put(JSONRequest.KEY_FORMAT,session.getAttribute(JSONRequest.KEY_FORMAT));
    }
    if (request.get(Controller.DEFAULTS) == null) {
      JSONObject defaults=(JSONObject)session.getAttribute(Controller.DEFAULTS);
      Set<Map.Entry<String,Object>> set=defaults == null ? null : defaults.entrySet();
      if (set != null) {
        for (        Map.Entry<String,Object> e : set) {
          if (e != null && request.get(e.getKey()) == null) {
            request.put(e.getKey(),e.getValue());
          }
        }
      }
    }
  }
  return super.parseResponse(request);
}
