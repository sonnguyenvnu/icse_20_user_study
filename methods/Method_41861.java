public void nodeLeft(ClusterEvent event){
  final String nodeLeft=event.getNode().getId();
  try {
    lock();
  }
 catch (  JobPersistenceException e) {
    getLog().info("Job store is already disabled, not processing nodeLeft() for " + nodeLeft);
    return;
  }
  try {
    List<TriggerWrapper> toEval=new ArrayList<TriggerWrapper>();
    for (    TriggerKey triggerKey : triggerFacade.allTriggerKeys()) {
      TriggerWrapper tw=triggerFacade.get(triggerKey);
      String clientId=tw.getLastTerracotaClientId();
      if (clientId != null && clientId.equals(nodeLeft)) {
        toEval.add(tw);
      }
    }
    for (    TriggerWrapper tw : toEval) {
      evalOrphanedTrigger(tw,false);
    }
    for (Iterator<FiredTrigger> iter=triggerFacade.allFiredTriggers().iterator(); iter.hasNext(); ) {
      FiredTrigger ft=iter.next();
      if (nodeLeft.equals(ft.getClientId())) {
        getLog().info("Found non-complete fired trigger: " + ft);
        iter.remove();
        TriggerWrapper tw=triggerFacade.get(ft.getTriggerKey());
        if (tw == null) {
          getLog().error("no trigger found for executing trigger: " + ft.getTriggerKey());
          continue;
        }
        scheduleRecoveryIfNeeded(tw,ft);
      }
    }
  }
  finally {
    unlock();
  }
  signaler.signalSchedulingChange(0);
}
