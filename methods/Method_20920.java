public static @NonNull Project halfWayProject(){
  return project().toBuilder().name("halfwayProject").goal(100.0f).pledged(50.0f).build();
}
