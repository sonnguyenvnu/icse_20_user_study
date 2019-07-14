public static @NonNull Map<String,Object> userProperties(final @NonNull User user,final @NonNull String prefix){
  final Map<String,Object> properties=new HashMap<String,Object>(){
{
      put("uid",user.id());
      put("backed_projects_count",user.backedProjectsCount());
      put("created_projects_count",user.createdProjectsCount());
      put("starred_projects_count",user.starredProjectsCount());
    }
  }
;
  return MapUtils.prefixKeys(properties,prefix);
}
