public void pickFile(){
  Intent chooserIntent=new Intent(Intent.ACTION_GET_CONTENT);
  chooserIntent.setType("image/*");
  startActivityForResult(chooserIntent,RESULT_CODE);
}
