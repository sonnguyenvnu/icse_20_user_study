public static String getDefaultDirectoryPath(){
  File dir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + DIRECTORY_NAME);
  if (!dir.exists())   dir.mkdir();
  return dir.getAbsolutePath();
}
