@Override public void unregister(@Nullable IComponentHostService moduleService){
  if (moduleService == null) {
    return;
  }
  moduleService.onDestory();
}
