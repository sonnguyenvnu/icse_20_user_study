private EntryList convert(RecordIterator<KeyValueEntry> entries) throws BackendException {
  try {
    return StaticArrayEntryList.ofStaticBuffer(entries,kvEntryGetter);
  }
  finally {
    try {
      entries.close();
    }
 catch (    IOException e) {
      throw new TemporaryBackendException(e);
    }
  }
}
