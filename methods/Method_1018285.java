@Override public void deploy() throws ArkRuntimeException {
  for (  Plugin plugin : pluginManagerService.getPluginsInOrder()) {
    try {
      deployPlugin(plugin);
    }
 catch (    ArkRuntimeException e) {
      LOGGER.error(String.format("Deploy plugin: %s meet error",plugin.getPluginName()),e);
      throw e;
    }
  }
}
