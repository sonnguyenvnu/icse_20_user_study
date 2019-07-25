private void init(JsonObject jObject){
  if (isNotSetOff()) {
    if (controlMode == 1) {
      if (jObject.get(JSONApiResponseKeysEnum.EMERGENCY_VALUE.getKey()) != null) {
        this.emergencyValue=jObject.get(JSONApiResponseKeysEnum.EMERGENCY_VALUE.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_KP.getKey()) != null) {
        this.ctrlKp=jObject.get(JSONApiResponseKeysEnum.CTRL_KP.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_TS.getKey()) != null) {
        this.ctrlTs=jObject.get(JSONApiResponseKeysEnum.CTRL_TS.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_TI.getKey()) != null) {
        this.ctrlTi=jObject.get(JSONApiResponseKeysEnum.CTRL_TI.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_KD.getKey()) != null) {
        this.ctrlKd=jObject.get(JSONApiResponseKeysEnum.CTRL_KD.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_MIN.getKey()) != null) {
        this.ctrlImin=jObject.get(JSONApiResponseKeysEnum.CTRL_MIN.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_MAX.getKey()) != null) {
        this.ctrlImax=jObject.get(JSONApiResponseKeysEnum.CTRL_MAX.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_Y_MIN.getKey()) != null) {
        this.ctrlYmin=jObject.get(JSONApiResponseKeysEnum.CTRL_Y_MIN.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_Y_MAX.getKey()) != null) {
        this.ctrlYmax=jObject.get(JSONApiResponseKeysEnum.CTRL_Y_MAX.getKey()).getAsFloat();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_KEEP_FLOOR_WARM.getKey()) != null) {
        this.ctrlKeepFloorWarm=jObject.get(JSONApiResponseKeysEnum.CTRL_KEEP_FLOOR_WARM.getKey()).getAsBoolean();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_ANTI_WIND_UP.getKey()) != null) {
        this.ctrlAntiWindUp=jObject.get(JSONApiResponseKeysEnum.CTRL_ANTI_WIND_UP.getKey()).getAsBoolean();
      }
    }
    if (controlMode == 2) {
      if (jObject.get(JSONApiResponseKeysEnum.REFERENCE_ZONE.getKey()) != null) {
        this.referenceZone=jObject.get(JSONApiResponseKeysEnum.REFERENCE_ZONE.getKey()).getAsInt();
      }
      if (jObject.get(JSONApiResponseKeysEnum.CTRL_OFFSET.getKey()) != null) {
        this.ctrlOffset=jObject.get(JSONApiResponseKeysEnum.CTRL_OFFSET.getKey()).getAsFloat();
      }
    }
  }
}
