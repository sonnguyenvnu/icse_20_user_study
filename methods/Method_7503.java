public static void getHostByName(String hostName,long address){
  ResolvedDomain resolvedDomain=dnsCache.get(hostName);
  if (resolvedDomain != null && SystemClock.elapsedRealtime() - resolvedDomain.ttl < 5 * 60 * 1000) {
    native_onHostNameResolved(hostName,address,resolvedDomain.getAddress());
  }
 else {
    ResolveHostByNameTask task=resolvingHostnameTasks.get(hostName);
    if (task == null) {
      task=new ResolveHostByNameTask(hostName);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      resolvingHostnameTasks.put(hostName,task);
    }
    task.addAddress(address);
  }
}
