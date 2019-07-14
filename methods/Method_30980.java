@NonNull public static Intent makeCaptureImage(@NonNull File outputFile,@NonNull Context context){
  return new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT,FileUtils.getContentUri(outputFile,context));
}
