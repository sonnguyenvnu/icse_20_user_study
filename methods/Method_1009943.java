@Override public void register(@Nullable IComponentHostService service){
  if (service == null) {
    return;
  }
  moduleServiceMap.put(service.getHost(),service);
  service.onCreate(Component.getApplication());
}
