private static Map<String,Object> jsonToMap(String json){
  if (Strings.isNullOrEmpty(json)) {
    return ImmutableMap.of();
  }
  return Optional.<Map<String,Object>>ofNullable(GSON.fromJson(json,MAP_TYPE_TOKEN.getType())).orElse(ImmutableMap.of());
}
