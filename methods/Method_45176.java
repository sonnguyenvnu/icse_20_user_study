protected final void addSettings(final ImmutableList<Setting<T>> thatSettings){
  for (  Setting<T> thatSetting : thatSettings) {
    addSetting(thatSetting);
  }
}
