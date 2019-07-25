public void start(){
  try {
    lock.lock();
    communicationHandler.addResponsePacketListener(this::handleResponsePacket);
    pendingLightState.addListener(this);
    ScheduledFuture<?> localSendJob=sendJob;
    if (localSendJob == null || localSendJob.isCancelled()) {
      sendJob=scheduler.scheduleWithFixedDelay(this::sendPendingPackets,0,PACKET_INTERVAL,TimeUnit.MILLISECONDS);
    }
  }
 catch (  Exception e) {
    logger.error("Error occurred while starting send packets job",e);
  }
 finally {
    lock.unlock();
  }
}
