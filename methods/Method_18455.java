static MountContentPool getPoolForComponent(Component component){
  if (ComponentsConfiguration.isPoolBisectEnabled && shouldDisablePool(component.getSimpleName())) {
    return new DisabledMountContentPool();
  }
  return component.onCreateMountContentPool();
}
