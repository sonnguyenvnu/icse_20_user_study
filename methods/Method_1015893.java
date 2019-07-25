@Override public void handle(ClientSettings settings) throws Exception {
  con.setSettings(settings);
  SettingsChangedEvent settingsEvent=new SettingsChangedEvent(con);
  bungee.getPluginManager().callEvent(settingsEvent);
}
