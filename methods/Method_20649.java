private String getTrackingData(final @NonNull String eventName,final @NonNull Map<String,Object> newProperties) throws JSONException {
  final JSONObject trackingEvent=new JSONObject();
  trackingEvent.put("event",eventName);
  final Map<String,Object> compactProperties=MapUtils.compact(newProperties);
  final JSONObject propertiesJSON=new JSONObject();
  for (  Map.Entry<String,Object> entry : compactProperties.entrySet()) {
    propertiesJSON.put(entry.getKey(),entry.getValue());
  }
  trackingEvent.put("properties",propertiesJSON);
  final JSONArray trackingArray=new JSONArray();
  trackingArray.put(trackingEvent);
  return trackingArray.toString();
}
