@Override public void invoke(@NotNull RoutingLoaderParameter parameter){
  RoutingRemoteFileStorage extensionInstance=RemoteWebServerUtil.getExtensionInstance(parameter.getProject(),RoutingRemoteFileStorage.class);
  if (extensionInstance == null) {
    return;
  }
  parameter.addRoutes(extensionInstance.getState().values());
}
