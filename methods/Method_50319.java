public static File createThumbnailSmallFileName(Context context,String originalPath){
  File storeFile=StorageUtils.getCacheDirectory(context);
  return new File(storeFile,"small_" + FilenameUtils.getName(originalPath));
}
