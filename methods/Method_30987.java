@NonNull public static Intent makePickOrCaptureImageWithChooser(boolean allowPickMultiple,@NonNull File captureOutputFile,@NonNull Context context){
  return makePickOrCaptureImageWithChooser(allowPickMultiple,FileUtils.getContentUri(captureOutputFile,context));
}
