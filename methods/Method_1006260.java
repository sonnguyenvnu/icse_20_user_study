@Subscribe public void listen(EntryRemovedEvent event){
  event.getBibEntry().getCiteKeyOptional().ifPresent(oldKey -> updateEntryLinks(null,oldKey));
}
