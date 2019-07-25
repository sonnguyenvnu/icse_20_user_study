public static void lock(){
  long lastStamp=0;
  for (  LifxLightCommunicationTracker tracker : trackers) {
    tracker.lock();
    lastStamp=Math.max(lastStamp,tracker.getTimestamp());
  }
  waitForNextPacketInterval(lastStamp);
}
