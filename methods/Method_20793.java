public static @NonNull Map<String,Object> discoveryParamsProperties(final @NonNull DiscoveryParams params,final @NonNull String prefix){
  final Map<String,Object> properties=Collections.unmodifiableMap(new HashMap<String,Object>(){
{
      put("everything",BooleanUtils.isTrue(params.isAllProjects()));
      put("recommended",BooleanUtils.isTrue(params.recommended()));
      put("social",BooleanUtils.isIntTrue(params.social()));
      put("staff_picks",BooleanUtils.isTrue(params.staffPicks()));
      put("starred",BooleanUtils.isIntTrue(params.starred()));
      put("term",params.term());
      put("sort",params.sort() != null ? String.valueOf(params.sort()) : "");
      final Category category=params.category();
      if (category != null) {
        putAll(categoryProperties(category));
      }
      final Location location=params.location();
      if (location != null) {
        putAll(locationProperties(location));
      }
    }
  }
);
  final Map<String,Object> prefixedProperties=MapUtils.prefixKeys(properties,prefix);
  prefixedProperties.put("page",params.page());
  prefixedProperties.put("per_page",params.perPage());
  return prefixedProperties;
}
