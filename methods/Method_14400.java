private static boolean saveStream(InputStream stream,URL url,File rawDataDir,final Progress progress,final SavingUpdate update,ObjectNode fileRecord,ArrayNode fileRecords,long length) throws IOException, Exception {
  String localname=url.getPath();
  if (localname.isEmpty() || localname.endsWith("/")) {
    localname=localname + "temp";
  }
  File file=allocateFile(rawDataDir,localname);
  JSONUtilities.safePut(fileRecord,"fileName",file.getName());
  JSONUtilities.safePut(fileRecord,"location",getRelativePath(file,rawDataDir));
  update.totalExpectedSize+=length;
  progress.setProgress("Downloading " + url.toString(),calculateProgressPercent(update.totalExpectedSize,update.totalRetrievedSize));
  long actualLength=saveStreamToFile(stream,file,update);
  JSONUtilities.safePut(fileRecord,"size",actualLength);
  if (actualLength == 0) {
    throw new Exception("No content found in " + url.toString());
  }
 else   if (length >= 0) {
    update.totalExpectedSize+=(actualLength - length);
  }
 else {
    update.totalExpectedSize+=actualLength;
  }
  progress.setProgress("Saving " + url.toString() + " locally",calculateProgressPercent(update.totalExpectedSize,update.totalRetrievedSize));
  return postProcessRetrievedFile(rawDataDir,file,fileRecord,fileRecords,progress);
}
