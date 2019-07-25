private void init(JsonObject jObject){
  if (isNotSetOff()) {
    if (jObject.get(JSONApiResponseKeysEnum.CONTROL_STATE.getKey()) != null) {
      this.controlState=jObject.get(JSONApiResponseKeysEnum.CONTROL_STATE.getKey()).getAsShort();
    }
    if (jObject.get(JSONApiResponseKeysEnum.OPERATION_MODE.getKey()) != null) {
      this.operationMode=jObject.get(JSONApiResponseKeysEnum.OPERATION_MODE.getKey()).getAsShort();
    }
    if (jObject.get(JSONApiResponseKeysEnum.TEMPERATION_VALUE.getKey()) != null) {
      this.temperature=jObject.get(JSONApiResponseKeysEnum.TEMPERATION_VALUE.getKey()).getAsFloat();
    }
    if (jObject.get(JSONApiResponseKeysEnum.NOMINAL_VALUE.getKey()) != null) {
      this.nominalValue=jObject.get(JSONApiResponseKeysEnum.NOMINAL_VALUE.getKey()).getAsFloat();
    }
    if (jObject.get(JSONApiResponseKeysEnum.CONTROL_VALUE.getKey()) != null) {
      this.controlValue=jObject.get(JSONApiResponseKeysEnum.CONTROL_VALUE.getKey()).getAsFloat();
    }
    if (jObject.get(JSONApiResponseKeysEnum.TEMPERATION_VALUE_TIME.getKey()) != null) {
      this.temperatureTime=jObject.get(JSONApiResponseKeysEnum.TEMPERATION_VALUE_TIME.getKey()).getAsString();
    }
    if (jObject.get(JSONApiResponseKeysEnum.NOMINAL_VALUE_TIME.getKey()) != null) {
      this.nominalValueTime=jObject.get(JSONApiResponseKeysEnum.NOMINAL_VALUE_TIME.getKey()).getAsString();
    }
    if (jObject.get(JSONApiResponseKeysEnum.CONTROL_VALUE_TIME.getKey()) != null) {
      this.controlValueTime=jObject.get(JSONApiResponseKeysEnum.CONTROL_VALUE_TIME.getKey()).getAsString();
    }
  }
}
