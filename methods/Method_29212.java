public void asyncMonitorMachineStats(final long hostId,final String ip){
  String key="monitor-machine-" + hostId + "-" + ip;
  asyncService.submitFuture(AsyncThreadPoolFactory.MACHINE_POOL,new KeyCallable<Boolean>(key){
    public Boolean execute(){
      try {
        monitorMachineStats(hostId,ip);
        return true;
      }
 catch (      Exception e) {
        logger.error(e.getMessage(),e);
        return false;
      }
    }
  }
);
}
