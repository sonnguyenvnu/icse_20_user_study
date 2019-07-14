private void processSnackbar(){
  currentEvent=eventQueue.poll();
  if (currentEvent != null) {
    eventsSet.remove(currentEvent);
    show(currentEvent);
  }
 else {
    processingQueue.getAndSet(false);
  }
}
