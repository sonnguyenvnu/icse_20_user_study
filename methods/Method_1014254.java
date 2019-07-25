@Override public void initialize(){
  logger.debug("Initializing SceneHandler");
  final Bridge bridge=getBridge();
  if (bridge != null) {
    bridgeStatusChanged(bridge.getStatusInfo());
  }
}
