public static @NonNull Project successfulProject(){
  return project().toBuilder().name("successfulProject").deadline(new DateTime().minus(2)).state(Project.STATE_SUCCESSFUL).build();
}
