@Override public void refresh(OwBaseBridgeHandler bridgeHandler,Boolean forcedRefresh) throws OwException {
  if (isConfigured) {
    List<State> states=bridgeHandler.readDecimalTypeArray(sensorId,counterParameter);
    logger.trace("read array {} from {}",states,sensorId);
    if (states.size() != 2) {
      throw new OwException("Expected exactly two values, got " + String.valueOf(states.size()));
    }
 else {
      callback.postUpdate(CHANNEL_COUNTER + "0",states.get(0));
      callback.postUpdate(CHANNEL_COUNTER + "1",states.get(1));
    }
  }
}
