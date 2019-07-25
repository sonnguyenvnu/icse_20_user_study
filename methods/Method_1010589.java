public void dispatch(@Nullable Collection<AbstractModelChangeEvent> events){
  if (events == null || events.isEmpty()) {
    return;
  }
  for (  SNodeBatchChangeListener l : myListeners) {
    l.processEvents(events);
  }
}
