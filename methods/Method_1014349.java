public void stop(){
  try {
    lock.lock();
    communicationHandler.removeResponsePacketListener(this::handleResponsePacket);
    ScheduledFuture<?> localStatePollingJob=statePollingJob;
    if (localStatePollingJob != null && !localStatePollingJob.isCancelled()) {
      localStatePollingJob.cancel(true);
      statePollingJob=null;
    }
  }
 catch (  Exception e) {
    logger.error("Error occurred while stopping light state updater",e);
  }
 finally {
    lock.unlock();
  }
}
