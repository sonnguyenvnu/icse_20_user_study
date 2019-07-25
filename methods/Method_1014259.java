private void init(JsonObject jObject){
  if (jObject.get(JSONApiResponseKeysEnum.SENSORS.getKey()) != null && jObject.get(JSONApiResponseKeysEnum.SENSORS.getKey()).isJsonArray()) {
    JsonArray jArray=jObject.get(JSONApiResponseKeysEnum.SENSORS.getKey()).getAsJsonArray();
    if (jArray.size() != 0) {
      sensors=new LinkedList<AssignSensorType>();
      Iterator<JsonElement> iter=jArray.iterator();
      while (iter.hasNext()) {
        JsonObject assignedSensor=iter.next().getAsJsonObject();
        Short sensorType=null;
        String meterDSUID=null;
        if (assignedSensor.get(JSONApiResponseKeysEnum.SENSOR_TYPE.getKey()) != null) {
          sensorType=assignedSensor.get(JSONApiResponseKeysEnum.SENSOR_TYPE.getKey()).getAsShort();
        }
        if (assignedSensor.get(JSONApiResponseKeysEnum.DSUID_LOWER_CASE.getKey()) != null) {
          meterDSUID=assignedSensor.get(JSONApiResponseKeysEnum.DSUID_LOWER_CASE.getKey()).getAsString();
        }
        sensors.add(new AssignSensorType(SensorEnum.getSensor(sensorType),meterDSUID));
      }
    }
  }
}
