@Subscribe public void listen(EntryAddedEvent addedEntryEvent){
  suggestionProviders.indexEntry(addedEntryEvent.getBibEntry());
}
