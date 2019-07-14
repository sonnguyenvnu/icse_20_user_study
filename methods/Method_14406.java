static public void postProcessSingleRetrievedFile(File file,ObjectNode fileRecord){
  if (!fileRecord.has("format")) {
    JSONUtilities.safePut(fileRecord,"format",ImportingManager.getFormat(file.getName(),JSONUtilities.getString(fileRecord,"declaredMimeType",null)));
  }
}
