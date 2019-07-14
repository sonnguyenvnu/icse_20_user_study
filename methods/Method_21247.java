public static @NonNull ActivityResult create(final int requestCode,final int resultCode,final @Nullable Intent intent){
  return ActivityResult.builder().requestCode(requestCode).resultCode(resultCode).intent(intent).build();
}
