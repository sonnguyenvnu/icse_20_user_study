static public String getFileSource(ObjectNode fileRecord){
  return JSONUtilities.getString(fileRecord,"url",JSONUtilities.getString(fileRecord,"fileName","unknown"));
}
