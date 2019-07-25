@Subscribe public synchronized void listen(@SuppressWarnings("unused") BibDatabaseContextChangedEvent event){
  startBackupTask();
}
