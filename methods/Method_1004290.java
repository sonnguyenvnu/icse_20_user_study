public synchronized void initialize() throws IOException {
  assert Thread.holdsLock(this);
  if (initialized) {
    return;
  }
  if (fileSystem.exists(journalFileBackup)) {
    if (fileSystem.exists(journalFile)) {
      fileSystem.delete(journalFileBackup);
    }
 else {
      fileSystem.rename(journalFileBackup,journalFile);
    }
  }
  if (fileSystem.exists(journalFile)) {
    try {
      readJournal();
      processJournal();
      initialized=true;
      return;
    }
 catch (    IOException journalIsCorrupt) {
    }
    try {
      delete();
    }
  finally {
      closed=false;
    }
  }
  rebuildJournal();
  initialized=true;
}
