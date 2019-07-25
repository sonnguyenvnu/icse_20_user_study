public void activate(){
  hueBridgeHandler.registerLightStatusListener(this);
  hueBridgeHandler.registerSensorStatusListener(this);
}
