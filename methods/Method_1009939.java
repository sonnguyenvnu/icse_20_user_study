@Override public void register(IComponentHostRouter router){
  if (router == null) {
    return;
  }
  hostRouterMap.put(router.getHost(),router);
  routerMap.putAll(router.getRouterMap());
}
