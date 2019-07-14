public static @NonNull Reward limited(){
  return reward().toBuilder().limit(10).remaining(5).build();
}
