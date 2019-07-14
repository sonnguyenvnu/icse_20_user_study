public static void openCameraImage(final Activity activity){
  imageUriFromCamera=createImagePathUri(activity);
  Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
  intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUriFromCamera);
  activity.startActivityForResult(intent,GET_IMAGE_BY_CAMERA);
}
