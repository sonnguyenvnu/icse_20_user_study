public static void lock(@Nullable MACAddress mac){
  if (mac != null) {
    LifxLightCommunicationTracker tracker=getOrCreateTracker(mac);
    tracker.lock();
    waitForNextPacketInterval(tracker.getTimestamp());
  }
 else {
    lock();
  }
}
