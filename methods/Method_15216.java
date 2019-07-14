/** 
 * ??????uri????
 * @param selectedImage
 */
private void sendPicByUri(Uri selectedImage){
  Cursor cursor=getContentResolver().query(selectedImage,null,null,null,null);
  if (cursor != null) {
    cursor.moveToFirst();
    int columnIndex=cursor.getColumnIndex("_data");
    picturePath=cursor.getString(columnIndex);
    cursor.close();
    cursor=null;
    if (picturePath == null || picturePath.equals("null")) {
      Toast toast=Toast.makeText(this,"?????",Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER,0,0);
      toast.show();
      return;
    }
  }
 else {
    File file=new File(selectedImage.getPath());
    if (!file.exists()) {
      Toast toast=Toast.makeText(this,"?????",Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER,0,0);
      toast.show();
      return;
    }
    picturePath=file.getAbsolutePath();
  }
  setResult(RESULT_OK,new Intent().putExtra(RESULT_PICTURE_PATH,picturePath));
}
