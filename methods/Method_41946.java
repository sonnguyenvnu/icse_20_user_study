public void onClick(View v){
  int which=-1;
  if (v == choosePicture1) {
    which=PICKED_ONE;
  }
 else   if (v == choosePicture2) {
    which=PICKED_TWO;
  }
  Intent choosePictureIntent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  startActivityForResult(choosePictureIntent,which);
}
