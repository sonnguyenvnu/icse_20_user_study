/** 
 * ?????????
 */
public static List<MediaBean> getMediaWithImageList(Context context,String bucketId,int page,int limit){
  int offset=(page - 1) * limit;
  List<MediaBean> mediaBeanList=new ArrayList<>();
  ContentResolver contentResolver=context.getContentResolver();
  List<String> projection=new ArrayList<>();
  projection.add(MediaStore.Images.Media._ID);
  projection.add(MediaStore.Images.Media.TITLE);
  projection.add(MediaStore.Images.Media.DATA);
  projection.add(MediaStore.Images.Media.BUCKET_ID);
  projection.add(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
  projection.add(MediaStore.Images.Media.MIME_TYPE);
  projection.add(MediaStore.Images.Media.DATE_ADDED);
  projection.add(MediaStore.Images.Media.DATE_MODIFIED);
  projection.add(MediaStore.Images.Media.LATITUDE);
  projection.add(MediaStore.Images.Media.LONGITUDE);
  projection.add(MediaStore.Images.Media.ORIENTATION);
  projection.add(MediaStore.Images.Media.SIZE);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    projection.add(MediaStore.Images.Media.WIDTH);
    projection.add(MediaStore.Images.Media.HEIGHT);
  }
  String selection=null;
  String[] selectionArgs=null;
  if (!TextUtils.equals(bucketId,String.valueOf(Integer.MIN_VALUE))) {
    selection=MediaStore.Images.Media.BUCKET_ID + "=?";
    selectionArgs=new String[]{bucketId};
  }
  Cursor cursor=contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection.toArray(new String[projection.size()]),selection,selectionArgs,MediaStore.Images.Media.DATE_ADDED + " DESC LIMIT " + limit + " OFFSET " + offset);
  if (cursor != null) {
    int count=cursor.getCount();
    if (count > 0) {
      cursor.moveToFirst();
      do {
        MediaBean mediaBean=parseImageCursorAndCreateThumImage(context,cursor);
        if (mediaBean != null) {
          mediaBeanList.add(mediaBean);
        }
      }
 while (cursor.moveToNext());
    }
  }
  if (cursor != null && !cursor.isClosed()) {
    cursor.close();
  }
  return mediaBeanList;
}
