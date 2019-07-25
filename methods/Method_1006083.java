@Subscribe public void listen(EntryAddedEvent entryAddedEvent){
  lock();
  try {
    list.add(entryAddedEvent.getBibEntry());
  }
  finally {
    unlock();
  }
}
