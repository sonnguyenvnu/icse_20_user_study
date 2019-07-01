void _XXXXX_() throws IOException {
synchronized (compactionLogLock) {
    if (compactionLogChannel == null) {
      compactionLogChannel=entryLogManager.createNewLogForCompaction();
    }
  }
}