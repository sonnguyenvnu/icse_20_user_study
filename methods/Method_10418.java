private byte[] getOfflineCacheFile(String cachePath,int level,int col,int row){
  byte[] bytes=null;
  File rowfile=new File(cachePath + "/" + level + "/" + col + "x" + row + ".cmap");
  if (rowfile.exists()) {
    try {
      bytes=CopySdcardbytes(rowfile);
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
 else {
    bytes=null;
  }
  return bytes;
}
