@Override public boolean isReachMaxDegradeIpCount(MeasureResultDetail measureResultDetail){
  InvocationStatDimension statDimension=measureResultDetail.getInvocationStatDimension();
  ConcurrentHashSet<String> ips=getDegradeProviders(statDimension.getDimensionKey());
  String ip=statDimension.getIp();
  if (ips.contains(ip)) {
    return false;
  }
 else {
    int degradeMaxIpCount=FaultToleranceConfigManager.getDegradeMaxIpCount(statDimension.getAppName());
    ipsLock.lock();
    try {
      if (ips.size() < degradeMaxIpCount) {
        ips.add(ip);
        return false;
      }
 else {
        return true;
      }
    }
  finally {
      ipsLock.unlock();
    }
  }
}
