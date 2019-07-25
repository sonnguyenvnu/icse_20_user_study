@Override public void unregister(IComponentHostRouter router){
  if (router == null) {
    return;
  }
  hostRouterMap.remove(router.getHost());
  Map<String,RouterBean> childRouterMap=router.getRouterMap();
  if (childRouterMap != null) {
    for (    String key : childRouterMap.keySet()) {
      routerMap.remove(key);
    }
  }
}
