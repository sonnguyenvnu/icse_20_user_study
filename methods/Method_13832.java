/** 
 * GET saved connections
 * @return
 */
public static List<DatabaseConfiguration> getSavedConnections(){
  ObjectMapper mapper=new ObjectMapper();
  try {
    String filename=getExtensionFilePath();
    File file=new File(filename);
    if (!file.exists()) {
      String dirPath=getExtensionFolder();
      File dirFile=new File(dirPath);
      boolean dirExists=true;
      if (!dirFile.exists()) {
        dirExists=dirFile.mkdir();
      }
      if (dirExists) {
        SavedConnectionContainer sc=new SavedConnectionContainer(new ArrayList<DatabaseConfiguration>());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename),sc);
        return sc.getSavedConnections();
      }
    }
    SavedConnectionContainer savedConnectionContainer=mapper.readValue(new File(filename),SavedConnectionContainer.class);
    return savedConnectionContainer.getSavedConnections();
  }
 catch (  JsonParseException e) {
    logger.error("JsonParseException: {}",e);
  }
catch (  JsonMappingException e) {
    logger.error("JsonMappingException: {}",e);
  }
catch (  IOException e) {
    logger.error("IOException: {}",e);
  }
  return null;
}
