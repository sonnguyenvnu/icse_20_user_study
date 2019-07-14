@NonNull public static Intent withChooser(@Nullable Intent intent){
  return Intent.createChooser(intent,null);
}
