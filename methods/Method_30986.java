@NonNull public static Intent makePickImage(boolean allowMultiple){
  return makePickFile(MIME_TYPE_IMAGE_ANY,allowMultiple);
}
