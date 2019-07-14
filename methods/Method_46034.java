protected ConcurrentHashSet<String> getDegradeProviders(String key){
  ConcurrentHashSet<String> ips=appServiceDegradeIps.get(key);
  if (ips == null) {
    ips=new ConcurrentHashSet<String>();
    ConcurrentHashSet<String> old=appServiceDegradeIps.putIfAbsent(key,ips);
    if (old != null) {
      ips=old;
    }
  }
  return ips;
}
