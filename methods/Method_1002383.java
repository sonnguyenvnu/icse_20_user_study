public void shutdown(DegraderLoadBalancerStrategyConfig config){
  if (config.getQuarantineMaxPercent() <= 0.0 || !_quarantineEnabled.get()) {
    return;
  }
  for (  Partition par : _partitions.values()) {
    Lock lock=par.getLock();
    lock.lock();
    try {
      PartitionDegraderLoadBalancerState curState=par.getState();
      curState.getQuarantineMap().values().forEach(DegraderLoadBalancerQuarantine::shutdown);
    }
  finally {
      lock.unlock();
    }
  }
}
