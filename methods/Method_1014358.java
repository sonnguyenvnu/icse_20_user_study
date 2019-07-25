public static void unlock(){
  for (  LifxLightCommunicationTracker tracker : trackers) {
    tracker.unlock();
  }
}
