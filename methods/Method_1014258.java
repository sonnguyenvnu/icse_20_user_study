private void init(JsonObject jObject){
  if (jObject.get(JSONApiResponseKeysEnum.CONTROL_MODE.getKey()) != null) {
    this.controlMode=jObject.get(JSONApiResponseKeysEnum.CONTROL_MODE.getKey()).getAsShort();
  }
  if (jObject.get(JSONApiResponseKeysEnum.CONTROL_DSUID.getKey()) != null) {
    this.controlDSUID=jObject.get(JSONApiResponseKeysEnum.CONTROL_DSUID.getKey()).getAsString();
  }
}
