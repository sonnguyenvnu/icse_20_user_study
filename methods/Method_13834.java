/** 
 * ADD to saved connections
 * @param dbConfig
 */
public static void addToSavedConnections(DatabaseConfiguration dbConfig){
  try {
    ObjectMapper mapper=new ObjectMapper();
    String savedConnectionFile=getExtensionFilePath();
    SavedConnectionContainer savedConnectionContainer=mapper.readValue(new File(savedConnectionFile),SavedConnectionContainer.class);
    savedConnectionContainer.getSavedConnections().add(dbConfig);
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
