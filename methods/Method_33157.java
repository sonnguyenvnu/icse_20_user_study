/** 
 * Shows  {@link SnackbarEvent SnackbarEvents} one by one. The next event will be shown after the current event's duration.
 * @param event the {@link SnackbarEvent event} to put in the queue.
 */
public void enqueue(SnackbarEvent event){
synchronized (this) {
    if (!eventsSet.contains(event)) {
      eventsSet.add(event);
      eventQueue.offer(event);
    }
 else     if (currentEvent == event && pauseTransition != null) {
      pauseTransition.playFromStart();
    }
  }
  if (processingQueue.compareAndSet(false,true)) {
    Platform.runLater(() -> {
      currentEvent=eventQueue.poll();
      if (currentEvent != null) {
        show(currentEvent);
      }
    }
);
  }
}
