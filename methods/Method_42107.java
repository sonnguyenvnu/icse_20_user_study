public static boolean renameMedia(Context context,Media media,String newName){
  boolean success=false;
  try {
    File from=new File(media.getPath());
    File to=new File(StringUtils.getPhotoPathRenamed(media.getPath(),newName));
    if (success=StorageHelper.moveFile(context,from,to)) {
      context.getContentResolver().delete(external,MediaStore.MediaColumns.DATA + "=?",new String[]{from.getPath()});
      scanFile(context,new String[]{to.getAbsolutePath()});
      media.setPath(to.getAbsolutePath());
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return success;
}
