@Override public Settings copy(){
  Settings copiedWriteSettings=writeSettings.copy();
  LinkedList<Settings> copiedSettingsList=new LinkedList<Settings>();
  for (  Settings settings : settingsList) {
    copiedSettingsList.add(settings.copy());
  }
  return new CompositeSettings(copiedWriteSettings,copiedSettingsList);
}
