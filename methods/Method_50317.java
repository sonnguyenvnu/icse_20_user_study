/** 
 * ????cursor???????
 */
private static MediaBean parseVideoCursorAndCreateThumImage(Context context,Cursor cursor){
  MediaBean mediaBean=new MediaBean();
  long id=cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media._ID));
  mediaBean.setId(id);
  String title=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
  mediaBean.setTitle(title);
  String originalPath=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
  mediaBean.setOriginalPath(originalPath);
  String bucketId=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID));
  mediaBean.setBucketId(bucketId);
  String bucketDisplayName=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
  mediaBean.setBucketDisplayName(bucketDisplayName);
  String mimeType=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.MIME_TYPE));
  mediaBean.setMimeType(mimeType);
  long createDate=cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DATE_ADDED));
  mediaBean.setCreateDate(createDate);
  long modifiedDate=cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DATE_MODIFIED));
  mediaBean.setModifiedDate(modifiedDate);
  long length=cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
  mediaBean.setLength(length);
  mediaBean.setThumbnailBigPath(createThumbnailBigFileName(context,originalPath).getAbsolutePath());
  mediaBean.setThumbnailSmallPath(createThumbnailSmallFileName(context,originalPath).getAbsolutePath());
  int width=0, height=0;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    width=cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.WIDTH));
    height=cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.HEIGHT));
  }
 else {
    try {
      ExifInterface exifInterface=new ExifInterface(originalPath);
      width=exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH,0);
      height=exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH,0);
    }
 catch (    IOException e) {
      Logger.e(e);
    }
  }
  mediaBean.setWidth(width);
  mediaBean.setHeight(height);
  double latitude=cursor.getDouble(cursor.getColumnIndex(MediaStore.Video.Media.LATITUDE));
  mediaBean.setLatitude(latitude);
  double longitude=cursor.getDouble(cursor.getColumnIndex(MediaStore.Video.Media.LONGITUDE));
  mediaBean.setLongitude(longitude);
  return mediaBean;
}
