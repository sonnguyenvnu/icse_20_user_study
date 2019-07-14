private void loadLocalUrls(){
  Uri externalContentUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
  String[] projection={MediaStore.Images.Media._ID};
  Cursor cursor=null;
  try {
    cursor=getContentResolver().query(externalContentUri,projection,null,null,null);
    mImageUrls.clear();
    int columnIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
    String imageId;
    Uri imageUri;
    while (cursor.moveToNext()) {
      imageId=cursor.getString(columnIndex);
      imageUri=Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,imageId);
      mImageUrls.add(imageUri.toString());
    }
  }
  finally {
    if (cursor != null) {
      cursor.close();
    }
  }
}
