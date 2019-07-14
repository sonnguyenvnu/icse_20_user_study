/** 
 * EDIT saved connections
 * @param jdbcConfig
 */
public static void editSavedConnections(DatabaseConfiguration jdbcConfig){
  if (logger.isDebugEnabled()) {
    logger.debug("Edit SavedConnections called with: {}",jdbcConfig);
  }
  try {
    ObjectMapper mapper=new ObjectMapper();
    String savedConnectionFile=getExtensionFilePath();
    SavedConnectionContainer savedConnectionContainer=mapper.readValue(new File(savedConnectionFile),SavedConnectionContainer.class);
    List<DatabaseConfiguration> savedConnections=savedConnectionContainer.getSavedConnections();
    ListIterator<DatabaseConfiguration> savedConnArrayIter=(ListIterator<DatabaseConfiguration>)savedConnections.listIterator();
    while (savedConnArrayIter.hasNext()) {
      DatabaseConfiguration sc=(DatabaseConfiguration)savedConnArrayIter.next();
      if (sc.getConnectionName().equals(jdbcConfig.getConnectionName())) {
        savedConnArrayIter.remove();
      }
    }
    savedConnections.add(jdbcConfig);
    savedConnectionContainer.setSavedConnections(savedConnections);
    mapper.writerWithDefaultPrettyPrinter().writeValue(new File(savedConnectionFile),savedConnectionContainer);
  }
 catch (  JsonGenerationException e1) {
    logger.error("JsonGenerationException: {}",e1);
    e1.printStackTrace();
  }
catch (  JsonMappingException e1) {
    logger.error("JsonMappingException: {}",e1);
    e1.printStackTrace();
  }
catch (  IOException e1) {
    logger.error("IOException: {}",e1);
    e1.printStackTrace();
  }
}
