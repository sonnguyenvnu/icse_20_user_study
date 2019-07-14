public static @NonNull Map<String,Object> updateProperties(final @NonNull Project project,final @NonNull Update update,final @Nullable User loggedInUser,final @NonNull String prefix){
  Map<String,Object> properties=new HashMap<String,Object>(){
{
      put("comments_count",update.commentsCount());
      put("has_liked",update.hasLiked());
      put("id",update.id());
      put("likes_count",update.likesCount());
      put("title",update.title());
      put("sequence",update.sequence());
      put("visible",update.visible());
      put("published_at",update.publishedAt());
    }
  }
;
  properties=MapUtils.prefixKeys(properties,prefix);
  properties.putAll(projectProperties(project,loggedInUser));
  return properties;
}
