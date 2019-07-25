@Subscribe public void listen(EntryAddedEvent entryAddedEvent){
  Optional<String> citekey=entryAddedEvent.getBibEntry().getCiteKeyOptional();
  if (citekey.isPresent()) {
    addKeyToSet(citekey.get());
  }
}
