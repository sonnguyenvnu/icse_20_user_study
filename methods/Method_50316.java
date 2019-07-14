/** 
 * ????cursor??????? <p> update 17.07.23 log <p> ???? Size ???????0??? Null????? No such file or directory
 */
@Nullable private static MediaBean parseImageCursorAndCreateThumImage(Context context,Cursor cursor){
  long size=cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
  String originalPath=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
  if (TextUtils.isEmpty(originalPath) || size <= 0 || !new File(originalPath).exists()) {
    return null;
  }
  long id=cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
  String title=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
  String bucketId=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID));
  String bucketDisplayName=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
  String mimeType=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.MIME_TYPE));
  long createDate=cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
  long modifiedDate=cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED));
  MediaBean mediaBean=new MediaBean();
  mediaBean.setId(id);
  mediaBean.setTitle(title);
  mediaBean.setOriginalPath(originalPath);
  mediaBean.setBucketId(bucketId);
  mediaBean.setBucketDisplayName(bucketDisplayName);
  mediaBean.setMimeType(mimeType);
  mediaBean.setCreateDate(createDate);
  mediaBean.setModifiedDate(modifiedDate);
  mediaBean.setThumbnailBigPath(createThumbnailBigFileName(context,originalPath).getAbsolutePath());
  mediaBean.setThumbnailSmallPath(createThumbnailSmallFileName(context,originalPath).getAbsolutePath());
  int width=0, height=0;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    width=cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
    height=cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
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
  double latitude=cursor.getDouble(cursor.getColumnIndex(MediaStore.Images.Media.LATITUDE));
  mediaBean.setLatitude(latitude);
  double longitude=cursor.getDouble(cursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE));
  mediaBean.setLongitude(longitude);
  int orientation=cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION));
  mediaBean.setOrientation(orientation);
  long length=cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
  mediaBean.setLength(length);
  return mediaBean;
}
