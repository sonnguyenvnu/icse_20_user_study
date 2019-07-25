@Subscribe public void listen(EntryChangedEvent entryChangedEvent){
  lock();
  try {
    for (int i=0; i < list.size(); i++) {
      if (list.get(i) == entryChangedEvent.getBibEntry()) {
        list.set(i,entryChangedEvent.getBibEntry());
        break;
      }
    }
  }
  finally {
    unlock();
  }
}
