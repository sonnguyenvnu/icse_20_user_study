public static void unlock(@Nullable MACAddress mac){
  if (mac != null) {
    LifxLightCommunicationTracker tracker=macTrackerMapping.get(mac);
    if (tracker != null) {
      tracker.unlock();
    }
  }
 else {
    unlock();
  }
}
