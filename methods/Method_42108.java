public static boolean copyMedia(Context context,Media media,String targetDir){
  boolean success=false;
  try {
    File from=new File(media.getPath());
    File to=new File(targetDir);
    if (success=StorageHelper.copyFile(context,from,to))     scanFile(context,new String[]{StringUtils.getPhotoPathMoved(media.getPath(),targetDir)});
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return success;
}
