public void shutdown(){
  writeOnlyRequestEventsSubject.onCompleted();
}
