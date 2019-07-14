public static @NonNull Project failedProject(){
  return project().toBuilder().name("failedProject").state(Project.STATE_FAILED).build();
}
