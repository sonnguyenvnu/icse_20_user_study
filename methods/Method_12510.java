protected void logError(Instance instance,Throwable ex){
  if (instance.getStatusInfo().isOffline()) {
    log.debug("Couldn't retrieve status for {}",instance,ex);
  }
 else {
    log.info("Couldn't retrieve status for {}",instance,ex);
  }
}
