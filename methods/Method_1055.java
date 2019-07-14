public void recordEvent(Event event){
  if (!sEnabled) {
    return;
  }
  if (mEventQueue.size() + 1 > MAX_EVENTS_TO_TRACK) {
    mEventQueue.poll();
  }
  mEventQueue.add(event);
}
