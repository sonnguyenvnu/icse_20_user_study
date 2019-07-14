public static @NonNull Project almostCompletedProject(){
  return project().toBuilder().name("almostCompleteProject").deadline(new DateTime().plusDays(1)).build();
}
