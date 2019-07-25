public static boolean activate(String uuid){
  if (!rendererMap.containsKey(uuid)) {
    LOGGER.debug("Activating upnp service for {}",uuid);
    return getInstance().addRenderer(uuid);
  }
  return true;
}
