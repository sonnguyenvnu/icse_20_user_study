public void flush(){
  checkNotDisposed();
  final List<SModelEvent> wrappedEvents;
synchronized (myEventsLock) {
    if (myEvents.isEmpty()) {
      return;
    }
    wrappedEvents=Collections.unmodifiableList(myEvents);
    myEvents=new ArrayList<>();
  }
  if (myModelAccess.canWrite()) {
    eventsHappened(wrappedEvents);
  }
 else {
    myModelAccess.runWriteAction(() -> eventsHappened(wrappedEvents));
  }
}
