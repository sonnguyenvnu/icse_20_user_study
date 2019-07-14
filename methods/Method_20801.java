public static @NonNull Map<String,Object> activityProperties(final @NonNull Activity activity,final @Nullable User loggedInUser,final @NonNull String prefix){
  Map<String,Object> properties=new HashMap<String,Object>(){
{
      put("category",activity.category());
    }
  }
;
  properties=MapUtils.prefixKeys(properties,prefix);
  final Project project=activity.project();
  if (project != null) {
    properties.putAll(projectProperties(project,loggedInUser));
    final Update update=activity.update();
    if (update != null) {
      properties.putAll(updateProperties(project,update,loggedInUser));
    }
  }
  return properties;
}
