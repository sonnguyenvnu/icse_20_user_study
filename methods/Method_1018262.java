/** 
 * executed only once
 */
public static void init(List<URL> confFiles){
  try {
    for (    URL url : confFiles) {
      loadConfigFile(url.openStream());
    }
  }
 catch (  Exception e) {
    throw new ArkRuntimeException("Catch Exception when load ArkConfigs",e);
  }
}
