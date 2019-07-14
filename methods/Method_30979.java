@NonNull public static Intent makeCaptureImage(@NonNull Uri outputUri){
  return new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT,outputUri);
}
