public static void openLocalImage(final Activity activity){
  Intent intent=new Intent();
  intent.setType("image/*");
  intent.setAction(Intent.ACTION_GET_CONTENT);
  activity.startActivityForResult(intent,GET_IMAGE_FROM_PHONE);
}
