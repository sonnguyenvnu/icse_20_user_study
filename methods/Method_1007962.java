@JsonCreator public static RegistryConfigs create(final Map<String,RegistryAuth> configs){
  if (configs == null) {
    return empty();
  }
  final Map<String,RegistryAuth> transformedMap=Maps.transformEntries(configs,new Maps.EntryTransformer<String,RegistryAuth,RegistryAuth>(){
    @Override public RegistryAuth transformEntry(    final String key,    final RegistryAuth value){
      if (value == null) {
        return null;
      }
      if (value.serverAddress() == null) {
        return value.toBuilder().serverAddress(key).build();
      }
      return value;
    }
  }
);
  return builder().configs(transformedMap).build();
}
