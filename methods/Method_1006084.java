@Subscribe public void listen(EntryRemovedEvent entryRemovedEvent){
  lock();
  try {
    list.remove(entryRemovedEvent.getBibEntry());
  }
  finally {
    unlock();
  }
}
