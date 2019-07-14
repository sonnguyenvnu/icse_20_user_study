public File saveStreamWithName(InputStream in,String attachmentFileName){
  File receiveDir=new File(TERMUX_RECEIVEDIR);
  if (!receiveDir.isDirectory() && !receiveDir.mkdirs()) {
    showErrorDialogAndQuit("Cannot create directory: " + receiveDir.getAbsolutePath());
    return null;
  }
  try {
    final File outFile=new File(receiveDir,attachmentFileName);
    try (FileOutputStream f=new FileOutputStream(outFile)){
      byte[] buffer=new byte[4096];
      int readBytes;
      while ((readBytes=in.read(buffer)) > 0) {
        f.write(buffer,0,readBytes);
      }
    }
     return outFile;
  }
 catch (  IOException e) {
    showErrorDialogAndQuit("Error saving file:\n\n" + e);
    Log.e("termux","Error saving file",e);
    return null;
  }
}
