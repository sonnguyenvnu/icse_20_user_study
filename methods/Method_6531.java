public static void fillPhotoSizeWithBytes(TLRPC.PhotoSize photoSize){
  if (photoSize == null || photoSize.bytes != null && photoSize.bytes.length != 0) {
    return;
  }
  File file=FileLoader.getPathToAttach(photoSize,true);
  try {
    RandomAccessFile f=new RandomAccessFile(file,"r");
    int len=(int)f.length();
    if (len < 20000) {
      photoSize.bytes=new byte[(int)f.length()];
      f.readFully(photoSize.bytes,0,photoSize.bytes.length);
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
}
