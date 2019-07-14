public static @NonNull Project doubledGoalProject(){
  return project().toBuilder().name("doubledGoalProject").goal(100.0f).pledged(200.0f).build();
}
