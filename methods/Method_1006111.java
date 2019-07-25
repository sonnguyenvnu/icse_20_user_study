@Subscribe public void listen(EntryChangedEvent event){
  if (jabRefPreferences.getTimestampPreferences().includeTimestamps()) {
    event.getBibEntry().setField(jabRefPreferences.getTimestampPreferences().getTimestampField(),jabRefPreferences.getTimestampPreferences().now());
  }
}
