@Override public void removed(Provider<Item> provider,Item element){
  super.removed(provider,element);
  for (  RegistryHook<Item> registryHook : registryHooks) {
    registryHook.afterRemoving(element);
  }
  if (provider instanceof ManagedItemProvider) {
    logger.debug("Item {} was removed, trying to clean up corresponding metadata",element.getUID());
    metadataRegistry.removeItemMetadata(element.getName());
  }
}
