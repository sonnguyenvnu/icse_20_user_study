@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.recordProgressChanged) {
    long t=(Long)args[0];
    progress=t / 60000.0f;
    recordedTime=t;
    invalidate();
  }
 else   if (id == NotificationCenter.FileDidUpload) {
    final String location=(String)args[0];
    if (cameraFile != null && cameraFile.getAbsolutePath().equals(location)) {
      file=(TLRPC.InputFile)args[1];
      encryptedFile=(TLRPC.InputEncryptedFile)args[2];
      size=(Long)args[5];
      if (encryptedFile != null) {
        key=(byte[])args[3];
        iv=(byte[])args[4];
      }
    }
  }
}
