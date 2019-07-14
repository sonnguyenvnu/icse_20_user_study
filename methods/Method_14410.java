static public File uncompressFile(File rawDataDir,InputStream uncompressedIS,ObjectNode fileRecord,final Progress progress) throws IOException {
  String fileName=JSONUtilities.getString(fileRecord,"location","unknown");
  for (  String ext : new String[]{".gz",".bz2"}) {
    if (fileName.endsWith(ext)) {
      fileName=fileName.substring(0,fileName.length() - ext.length());
      break;
    }
  }
  File file2=allocateFile(rawDataDir,fileName);
  progress.setProgress("Uncompressing " + fileName,-1);
  saveStreamToFile(uncompressedIS,file2,null);
  JSONUtilities.safePut(fileRecord,"declaredEncoding",(String)null);
  JSONUtilities.safePut(fileRecord,"declaredMimeType",(String)null);
  JSONUtilities.safePut(fileRecord,"location",getRelativePath(file2,rawDataDir));
  return file2;
}
