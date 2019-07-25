@Override public void handle(PluginMessage pluginMessage) throws Exception {
  if (PluginMessage.SHOULD_RELAY.apply(pluginMessage)) {
    relayMessages.add(pluginMessage);
  }
}
