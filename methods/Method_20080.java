private void startCameraIntentForResult(){
  imageUri=null;
  preview.setImageBitmap(null);
  Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
  if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
    ContentValues values=new ContentValues();
    values.put(MediaStore.Images.Media.TITLE,"New Picture");
    values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera");
    imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
    startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
  }
}
