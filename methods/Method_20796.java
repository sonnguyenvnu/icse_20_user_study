public static @NonNull Map<String,Object> locationProperties(final @NonNull Location location,final @NonNull String prefix){
  final Map<String,Object> properties=new HashMap<String,Object>(){
{
      put("id",location.id());
      put("name",location.name());
      put("displayable_name",location.displayableName());
      put("city",location.city());
      put("state",location.state());
      put("country",location.country());
      put("projects_count",location.projectsCount());
    }
  }
;
  return MapUtils.prefixKeys(properties,prefix);
}
