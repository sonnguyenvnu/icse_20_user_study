@Nullable private String getLocalFilePath(ImageRequest imageRequest){
  Uri uri=imageRequest.getSourceUri();
  if (UriUtil.isLocalFileUri(uri)) {
    return imageRequest.getSourceFile().getPath();
  }
 else   if (UriUtil.isLocalContentUri(uri)) {
    String selection=null;
    String[] selectionArgs=null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && "com.android.providers.media.documents".equals(uri.getAuthority())) {
      String documentId=DocumentsContract.getDocumentId(uri);
      uri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
      selection=MediaStore.Video.Media._ID + "=?";
      selectionArgs=new String[]{documentId.split(":")[1]};
    }
    Cursor cursor=mContentResolver.query(uri,new String[]{MediaStore.Video.Media.DATA},selection,selectionArgs,null);
    try {
      if (cursor != null && cursor.moveToFirst()) {
        return cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
      }
    }
  finally {
      if (cursor != null) {
        cursor.close();
      }
    }
  }
  return null;
}
