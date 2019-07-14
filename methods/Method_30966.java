public static File makeSaveImageOutputFile(String fileName){
  File directory=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),SUB_DIRECTORY_NAME);
  directory.mkdirs();
  return new File(directory,fileName);
}
