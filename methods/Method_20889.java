public static @NonNull Activity updateActivity(){
  return activity().toBuilder().category(Activity.CATEGORY_UPDATE).project(ProjectFactory.project()).user(UserFactory.user()).build();
}
