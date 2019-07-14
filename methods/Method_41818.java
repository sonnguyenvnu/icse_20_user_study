@Override public void schedulerStarted() throws SchedulerException {
  clusterInfo.addClusterListener(this);
  Collection<ClusterNode> nodes=clusterInfo.getNodes();
  Set<String> activeClientIDs=new HashSet<String>();
  for (  ClusterNode node : nodes) {
    boolean added=activeClientIDs.add(node.getId());
    if (!added) {
      getLog().error("DUPLICATE node ID detected: " + node);
    }
  }
  lock();
  try {
    List<TriggerWrapper> toEval=new ArrayList<TriggerWrapper>();
    for (    TriggerKey triggerKey : triggerFacade.allTriggerKeys()) {
      TriggerWrapper tw=triggerFacade.get(triggerKey);
      String lastTerracotaClientId=tw.getLastTerracotaClientId();
      if (lastTerracotaClientId == null) {
        continue;
      }
      if (!activeClientIDs.contains(lastTerracotaClientId) || tw.getState() == TriggerState.ERROR) {
        toEval.add(tw);
      }
    }
    for (    TriggerWrapper tw : toEval) {
      evalOrphanedTrigger(tw,true);
    }
    for (Iterator<FiredTrigger> iter=triggerFacade.allFiredTriggers().iterator(); iter.hasNext(); ) {
      FiredTrigger ft=iter.next();
      if (!activeClientIDs.contains(ft.getClientId())) {
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
}
