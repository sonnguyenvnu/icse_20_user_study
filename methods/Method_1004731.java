@Override public Settings copy(){
  return new SettingsView(parent.copy(),namespace);
}
