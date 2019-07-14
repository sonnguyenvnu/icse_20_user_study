public static void hideAlbum(String path,Context context){
  File dirName=new File(path);
  File file=new File(dirName,".nomedia");
  if (!file.exists()) {
    try {
      FileOutputStream out=new FileOutputStream(file);
      out.flush();
      out.close();
      scanFile(context,new String[]{file.getAbsolutePath()});
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
