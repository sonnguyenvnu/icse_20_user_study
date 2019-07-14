@Override public ObjectNode createParserUIInitializationData(ImportingJob job,java.util.List<ObjectNode> fileRecords,String format){
  if (fileRecords.size() > 0) {
    ObjectNode firstFileRecord=fileRecords.get(0);
    File file=ImportingUtilities.getFile(job,firstFileRecord);
    File tempFile=new File(file.getAbsolutePath() + ".xml");
    try {
      InputStream inputStream=new FileInputStream(file);
      OutputStream outputStream=new FileOutputStream(tempFile);
      try {
        MarcWriter writer=new MarcXmlWriter(outputStream,true);
        MarcPermissiveStreamReader reader=new MarcPermissiveStreamReader(inputStream,true,true);
        while (reader.hasNext()) {
          Record record=reader.next();
          writer.write(record);
        }
        writer.close();
      }
  finally {
        try {
          outputStream.close();
          inputStream.close();
          if (tempFile.length() == 0)           tempFile.delete();
 else           JSONUtilities.safePut(firstFileRecord,"location",JSONUtilities.getString(firstFileRecord,"location","") + ".xml");
        }
 catch (        IOException e) {
        }
      }
    }
 catch (    IOException e) {
      logger.error("Failed to create temporary XML file from MARC file",e);
    }
  }
  ObjectNode options=super.createParserUIInitializationData(job,fileRecords,format);
  return options;
}
