public static @NonNull Activity projectStateChangedPositiveActivity(){
  return activity().toBuilder().category(Activity.CATEGORY_SUCCESS).project(ProjectFactory.successfulProject()).build();
}
