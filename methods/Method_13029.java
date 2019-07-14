protected DelegatedEntry getDelegatedEntry(Container.Entry entry){
  URI uri=entry.getUri();
  DelegatedEntry delegatedEntry=uriToDelegatedEntry.get(uri);
  if (delegatedEntry == null) {
    uriToDelegatedEntry.put(uri,delegatedEntry=new DelegatedEntry(entry));
  }
  return delegatedEntry;
}
