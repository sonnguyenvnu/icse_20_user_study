public static @NonNull Map<String,Object> projectProperties(final @NonNull Project project,final @Nullable User loggedInUser,final @NonNull String prefix){
  final Map<String,Object> properties=new HashMap<String,Object>(){
{
      put("backers_count",project.backersCount());
      put("comments_count",project.commentsCount());
      put("country",project.country());
      put("duration",Math.round(ProjectUtils.timeInSecondsOfDuration(project)));
      put("currency",project.currency());
      put("goal",project.goal());
      put("has_video",project.video() != null);
      put("hours_remaining",(int)Math.ceil(ProjectUtils.timeInSecondsUntilDeadline(project) / 60.0f / 60.0f));
      put("name",project.name());
      put("percent_raised",project.percentageFunded() / 100.0f);
      put("pid",project.id());
      put("pledged",project.pledged());
      put("state",project.state());
      put("update_count",project.updatesCount());
      final Category category=project.category();
      if (category != null) {
        put("category",category.name());
        final Category parent=category.parent();
        if (parent != null) {
          put("parent_category",parent.name());
        }
      }
      final Location location=project.location();
      if (location != null) {
        put("location",location.name());
      }
    }
  }
;
  final Map<String,Object> prefixedMap=MapUtils.prefixKeys(properties,prefix);
  prefixedMap.putAll(userProperties(project.creator(),"creator_"));
  if (loggedInUser != null) {
    prefixedMap.put("user_is_project_creator",ProjectUtils.userIsCreator(project,loggedInUser));
    prefixedMap.put("user_is_backer",project.isBacking());
    prefixedMap.put("user_has_starred",project.isStarred());
  }
  return prefixedMap;
}
