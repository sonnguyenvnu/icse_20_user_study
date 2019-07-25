private void init(JsonObject jObject){
  if (jObject.get(JSONApiResponseKeysEnum.IS_CONFIGURED.getKey()) != null) {
    this.isConfigured=jObject.get(JSONApiResponseKeysEnum.IS_CONFIGURED.getKey()).getAsBoolean();
  }
  if (isConfigured) {
    if (jObject.get(JSONApiResponseKeysEnum.CONTROL_DSUID.getKey()) != null) {
      this.controlDSUID=jObject.get(JSONApiResponseKeysEnum.CONTROL_DSUID.getKey()).getAsString();
    }
    temperatureControlValues=new HashMap<OperationModes,Float>(OperationModes.values().length);
    for (    OperationModes opMode : OperationModes.values()) {
      if (jObject.get(opMode.getKey()) != null) {
        temperatureControlValues.put(opMode,jObject.get(opMode.getKey()).getAsFloat());
      }
    }
  }
}
