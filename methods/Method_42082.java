public static void unHideAlbum(String path,Context context){
  File dirName=new File(path);
  File file=new File(dirName,".nomedia");
  if (file.exists()) {
    if (file.delete())     scanFile(context,new String[]{file.getAbsolutePath()});
  }
}
