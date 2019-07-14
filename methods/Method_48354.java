@Override public synchronized boolean unregisterReader(MessageReader reader){
  ResourceUnavailableException.verifyOpen(isOpen,"Log",name);
  return this.readers.remove(reader);
}
