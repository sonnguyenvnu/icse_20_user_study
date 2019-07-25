public void stop(){
  try {
    lock.lock();
    communicationHandler.removeResponsePacketListener(this::handleResponsePacket);
    pendingLightState.removeListener(this);
    ScheduledFuture<?> localSendJob=sendJob;
    if (localSendJob != null && !localSendJob.isCancelled()) {
      localSendJob.cancel(true);
      sendJob=null;
    }
    pendingPacketsMap.clear();
  }
 catch (  Exception e) {
    logger.error("Error occurred while stopping send packets job",e);
  }
 finally {
    lock.unlock();
  }
}
