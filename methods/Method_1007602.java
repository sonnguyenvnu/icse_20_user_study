@SuppressWarnings("unchecked") private static <V extends Keyed>RegistryConverter<V> from(Class<Keyed> registryType){
  try {
    Field registryField=registryType.getDeclaredField("REGISTRY");
    Registry<V> registry=(Registry<V>)registryField.get(null);
    return new RegistryConverter<>(registry);
  }
 catch (  NoSuchFieldException e) {
    throw new IllegalArgumentException("Not a registry-backed type: " + registryType.getName());
  }
catch (  IllegalAccessException e) {
    throw new IllegalStateException("Registry field inaccessible on " + registryType.getName());
  }
}
