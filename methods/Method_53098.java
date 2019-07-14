static Map<String,RateLimitStatus> createRateLimitStatuses(JSONObject json) throws TwitterException {
  Map<String,RateLimitStatus> map=new HashMap<String,RateLimitStatus>();
  try {
    JSONObject resources=json.getJSONObject("resources");
    Iterator resourceKeys=resources.keys();
    while (resourceKeys.hasNext()) {
      JSONObject resource=resources.getJSONObject((String)resourceKeys.next());
      Iterator endpointKeys=resource.keys();
      while (endpointKeys.hasNext()) {
        String endpoint=(String)endpointKeys.next();
        JSONObject rateLimitStatusJSON=resource.getJSONObject(endpoint);
        RateLimitStatus rateLimitStatus=new RateLimitStatusJSONImpl(rateLimitStatusJSON);
        map.put(endpoint,rateLimitStatus);
      }
    }
    return Collections.unmodifiableMap(map);
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
