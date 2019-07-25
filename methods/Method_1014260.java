private void init(JsonObject jObject){
  if (jObject.get(JSONApiResponseKeysEnum.VALUES.getKey()).isJsonArray()) {
    JsonArray jArray=jObject.get(JSONApiResponseKeysEnum.VALUES.getKey()).getAsJsonArray();
    if (jArray.size() != 0) {
      Iterator<JsonElement> iter=jArray.iterator();
      while (iter.hasNext()) {
        JsonObject cachedSensorValue=iter.next().getAsJsonObject();
        super.addSensorValue(cachedSensorValue,false);
      }
    }
  }
}
