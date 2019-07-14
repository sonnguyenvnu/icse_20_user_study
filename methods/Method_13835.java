public static void deleteAllSavedConnections(){
  if (logger.isDebugEnabled()) {
    logger.debug("delete All Saved Connections called...");
  }
  try {
    List<DatabaseConfiguration> savedConnections=getSavedConnections();
    if (logger.isDebugEnabled()) {
      logger.debug("Size before delete SavedConnections :: {}",savedConnections.size());
    }
    ArrayList<DatabaseConfiguration> newSavedConns=new ArrayList<DatabaseConfiguration>();
    ObjectMapper mapper=new ObjectMapper();
    String savedConnectionFile=getExtensionFilePath();
    SavedConnectionContainer savedConnectionContainer=mapper.readValue(new File(savedConnectionFile),SavedConnectionContainer.class);
    savedConnectionContainer.setSavedConnections(newSavedConns);
    if (logger.isDebugEnabled()) {
      logger.debug("Size after delete SavedConnections :: {}",savedConnectionContainer.getSavedConnections().size());
    }
    mapper.writerWithDefaultPrettyPrinter().writeValue(new File(savedConnectionFile),savedConnectionContainer);
  }
 catch (  JsonGenerationException e1) {
    logger.error("JsonGenerationException: {}",e1);
  }
catch (  JsonMappingException e1) {
    logger.error("JsonMappingException: {}",e1);
  }
catch (  IOException e1) {
    logger.error("IOException: {}",e1);
  }
}
