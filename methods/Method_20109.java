private void launchCamera(){
  Log.d(TAG,"launchCamera");
  Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
  intent.setType("image/*");
  startActivityForResult(intent,RC_TAKE_PICTURE);
}
