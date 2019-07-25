@Subscribe public void listen(EntryChangedEvent entryChangedEvent){
  suggestionProviders.indexEntry(entryChangedEvent.getBibEntry());
}
