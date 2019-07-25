@PreDestroy public void close(){
  this.sessions.removeEntryListener(this.sessionListenerId);
}
