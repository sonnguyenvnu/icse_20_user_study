private static CustomTabsSession getCurrentSession(){
  return customTabsCurrentSession == null ? null : customTabsCurrentSession.get();
}
