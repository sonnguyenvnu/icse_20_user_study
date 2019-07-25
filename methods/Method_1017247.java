public static Supplier<BackendType> factory(){
  return () -> {
    final Map<String,Map<String,Object>> mappings=new HashMap<>();
    mappings.put(TAG_TYPE,loadJsonResource("kv/tag.json",variables(ImmutableMap.of("type",TAG_TYPE))));
    mappings.put(SERIES_TYPE,loadJsonResource("kv/series.json",variables(ImmutableMap.of("type",SERIES_TYPE))));
    final Map<String,Object> settings=loadJsonResource("kv/settings.json",Function.identity());
    return new BackendType(mappings,settings,SuggestBackendKV.class);
  }
;
}
