@Override protected MountContentPool onCreateMountContentPool(){
  if (ComponentsConfiguration.disableComponentHostPool) {
    return new DisabledMountContentPool();
  }
  return super.onCreateMountContentPool();
}
