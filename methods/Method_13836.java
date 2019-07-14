/** 
 * DELETE saved connections
 * @param connectionName
 */
public static void deleteSavedConnections(String connectionName){
  if (logger.isDebugEnabled()) {
    logger.debug("deleteSavedConnections called with: {}",connectionName);
  }
  try {
    List<DatabaseConfiguration> savedConnections=getSavedConnections();
    ;
    if (logger.isDebugEnabled()) {
      logger.debug("Size before delete SavedConnections :: {}",savedConnections.size());
    }
    ArrayList<DatabaseConfiguration> newSavedConns=new ArrayList<DatabaseConfiguration>();
    for (    DatabaseConfiguration dc : savedConnections) {
      if (!dc.getConnectionName().equalsIgnoreCase(connectionName.trim())) {
        newSavedConns.add(dc);
      }
    }
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
