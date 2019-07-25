@Override public void refresh(OwBaseBridgeHandler bridgeHandler,Boolean forcedRefresh) throws OwException {
  if (isConfigured) {
    State state;
    BitSet statesSensed=bridgeHandler.readBitSet(sensorId,fullInParam);
    BitSet statesPIO=bridgeHandler.readBitSet(sensorId,fullOutParam);
    for (int i=0; i < ioConfig.size(); i++) {
      if (ioConfig.get(i).isInput()) {
        state=ioConfig.get(i).convertState(statesSensed.get(i));
        logger.trace("{} IN{}: raw {}, final {}",sensorId,i,statesSensed,state);
      }
 else {
        state=ioConfig.get(i).convertState(statesPIO.get(i));
        logger.trace("{} OUT{}: raw {}, final {}",sensorId,i,statesPIO,state);
      }
      callback.postUpdate(ioConfig.get(i).getChannelId(),state);
    }
  }
}
