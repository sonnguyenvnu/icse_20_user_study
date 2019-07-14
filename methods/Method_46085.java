private void deregisterConsulService(String id){
  consulClient.agentServiceDeregister(id);
  ScheduledFuture future=heartbeatFutures.remove(id);
  if (future != null) {
    future.cancel(true);
  }
}
