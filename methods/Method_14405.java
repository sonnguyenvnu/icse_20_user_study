static public boolean postProcessRetrievedFile(File rawDataDir,File file,ObjectNode fileRecord,ArrayNode fileRecords,final Progress progress){
  String mimeType=JSONUtilities.getString(fileRecord,"declaredMimeType",null);
  String contentEncoding=JSONUtilities.getString(fileRecord,"declaredEncoding",null);
  InputStream archiveIS=tryOpenAsArchive(file,mimeType,contentEncoding);
  if (archiveIS != null) {
    try {
      if (explodeArchive(rawDataDir,archiveIS,fileRecord,fileRecords,progress)) {
        file.delete();
        return true;
      }
    }
  finally {
      try {
        archiveIS.close();
      }
 catch (      IOException e) {
      }
    }
  }
  InputStream uncompressedIS=tryOpenAsCompressedFile(file,mimeType,contentEncoding);
  if (uncompressedIS != null) {
    try {
      File file2=uncompressFile(rawDataDir,uncompressedIS,fileRecord,progress);
      file.delete();
      file=file2;
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
 finally {
      try {
        uncompressedIS.close();
      }
 catch (      IOException e) {
      }
    }
  }
  postProcessSingleRetrievedFile(file,fileRecord);
  JSONUtilities.append(fileRecords,fileRecord);
  return false;
}
