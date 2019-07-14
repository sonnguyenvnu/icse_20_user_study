public static long folderSize(SmbFile directory){
  long length=0;
  try {
    for (    SmbFile file : directory.listFiles()) {
      if (file.isFile())       length+=file.length();
 else       length+=folderSize(file);
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return length;
}
