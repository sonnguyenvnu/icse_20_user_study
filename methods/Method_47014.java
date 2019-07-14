private synchronized void sendMessages(){
  lastMessagedTime=Calendar.getInstance().getTimeInMillis();
synchronized (pathsAdded) {
    for (    String pathAdded : pathsAdded) {
      handler.obtainMessage(NEW_ITEM,pathAdded).sendToTarget();
    }
  }
  pathsAdded.clear();
synchronized (pathsRemoved) {
    for (    String pathRemoved : pathsRemoved) {
      handler.obtainMessage(DELETED_ITEM,pathRemoved).sendToTarget();
    }
  }
  pathsRemoved.clear();
  messagingScheduled=false;
}
