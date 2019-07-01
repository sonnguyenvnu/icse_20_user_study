public boolean _XXXXX_(long ledgerId,long previousLAC,Watcher<LastAddConfirmedUpdateNotification> watcher) throws IOException {
  LedgerDescriptor handle=handles.getReadOnlyHandle(ledgerId);
  return handle.waitForLastAddConfirmedUpdate(previousLAC,watcher);
}