/** 
 * ??????media???
 */
public static List<BucketBean> getAllBucket(Context context,boolean isImage){
  List<BucketBean> bucketBeenList=new ArrayList<>();
  ContentResolver contentResolver=context.getContentResolver();
  String[] projection;
  if (isImage) {
    projection=new String[]{MediaStore.Images.Media.BUCKET_ID,MediaStore.Images.Media.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.ORIENTATION};
  }
 else {
    projection=new String[]{MediaStore.Video.Media.BUCKET_ID,MediaStore.Video.Media.DATA,MediaStore.Video.Media.BUCKET_DISPLAY_NAME};
  }
  BucketBean allMediaBucket=new BucketBean();
  allMediaBucket.setBucketId(String.valueOf(Integer.MIN_VALUE));
  Uri uri;
  if (isImage) {
    allMediaBucket.setBucketName(context.getString(R.string.gallery_all_image));
    uri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
  }
 else {
    allMediaBucket.setBucketName(context.getString(R.string.gallery_all_video));
    uri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
  }
  bucketBeenList.add(allMediaBucket);
  Cursor cursor=null;
  try {
    cursor=contentResolver.query(uri,projection,null,null,MediaStore.Video.Media.DATE_ADDED + " DESC");
  }
 catch (  Exception e) {
    Logger.e(e);
  }
  if (cursor != null && cursor.getCount() > 0) {
    cursor.moveToFirst();
    do {
      BucketBean bucketBean=new BucketBean();
      String bucketId;
      String bucketKey;
      String cover;
      if (isImage) {
        bucketId=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID));
        bucketBean.setBucketId(bucketId);
        String bucketDisplayName=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
        bucketBean.setBucketName(bucketDisplayName);
        bucketKey=MediaStore.Images.Media.BUCKET_ID;
        cover=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        int orientation=cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION));
        bucketBean.setOrientation(orientation);
      }
 else {
        bucketId=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID));
        bucketBean.setBucketId(bucketId);
        String bucketDisplayName=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
        bucketBean.setBucketName(bucketDisplayName);
        bucketKey=MediaStore.Video.Media.BUCKET_ID;
        cover=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
      }
      if (TextUtils.isEmpty(allMediaBucket.getCover())) {
        allMediaBucket.setCover(cover);
      }
      if (bucketBeenList.contains(bucketBean)) {
        continue;
      }
      Cursor c=contentResolver.query(uri,projection,bucketKey + "=?",new String[]{bucketId},null);
      if (c != null && c.getCount() > 0) {
        bucketBean.setImageCount(c.getCount());
      }
      bucketBean.setCover(cover);
      if (c != null && !c.isClosed()) {
        c.close();
      }
      bucketBeenList.add(bucketBean);
    }
 while (cursor.moveToNext());
  }
  if (cursor != null && !cursor.isClosed()) {
    cursor.close();
  }
  return bucketBeenList;
}
