public static void openLocalImage(final Fragment fragment){
  Intent intent=new Intent();
  intent.setType("image/*");
  intent.setAction(Intent.ACTION_GET_CONTENT);
  fragment.startActivityForResult(intent,GET_IMAGE_FROM_PHONE);
}
