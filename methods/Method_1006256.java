@Subscribe public void listen(EntryRemovedEvent entryRemovedEvent){
  Optional<String> citeKey=entryRemovedEvent.getBibEntry().getCiteKeyOptional();
  if (citeKey.isPresent()) {
    removeKeyFromSet(citeKey.get());
  }
}
