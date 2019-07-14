public static @NonNull Activity projectStateChangedActivity(){
  return activity().toBuilder().category(Activity.CATEGORY_FAILURE).project(ProjectFactory.failedProject()).build();
}
