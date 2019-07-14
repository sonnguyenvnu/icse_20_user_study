private void registerConsulService(NewService service){
  consulClient.agentServiceRegister(service);
  if (service.getCheck().getTtl() != null) {
    ScheduledFuture<?> scheduledFuture=heartbeatExecutor.scheduleAtFixedRate(() -> checkPass(service),0,properties.getHeartbeatInterval(),TimeUnit.MILLISECONDS);
    ScheduledFuture oldFuture=heartbeatFutures.remove(service.getId());
    if (oldFuture != null) {
      oldFuture.cancel(true);
    }
    heartbeatFutures.put(service.getId(),scheduledFuture);
  }
}
