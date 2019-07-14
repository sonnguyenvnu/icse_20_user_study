@Override public boolean isExistInTheDegradeList(MeasureResultDetail measureResultDetail){
  InvocationStatDimension statDimension=measureResultDetail.getInvocationStatDimension();
  ConcurrentHashSet<String> ips=getDegradeProviders(statDimension.getDimensionKey());
  return ips != null && ips.contains(statDimension.getIp());
}
