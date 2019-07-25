public void stop(){
  try {
    lock.lock();
    communicationHandler.removeResponsePacketListener(this::handleResponsePacket);
    ScheduledFuture<?> localUpdateJob=updateJob;
    if (localUpdateJob != null && !localUpdateJob.isCancelled()) {
      localUpdateJob.cancel(true);
      updateJob=null;
    }
  }
 catch (  Exception e) {
    logger.error("Error occurred while stopping properties update job for a light ({})",logId,e);
  }
 finally {
    lock.unlock();
  }
}
