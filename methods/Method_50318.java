public static File createThumbnailBigFileName(Context context,String originalPath){
  File storeFile=StorageUtils.getCacheDirectory(context);
  return new File(storeFile,"big_" + FilenameUtils.getName(originalPath));
}
