/** 
 * Loads the configuration from this configuration file.
 * @param configFile    The configuration file to load.
 * @param encoding      The encoding of the configuration file.
 * @param failIfMissing Whether to fail if the file is missing.
 * @return The properties from the configuration file. An empty Map if none.
 * @throws FlywayException when the configuration file could not be loaded.
 */
public static Map<String,String> loadConfigurationFile(File configFile,String encoding,boolean failIfMissing) throws FlywayException {
  String errorMessage="Unable to load config file: " + configFile.getAbsolutePath();
  if (!configFile.isFile() || !configFile.canRead()) {
    if (!failIfMissing) {
      LOG.debug(errorMessage);
      return new HashMap<>();
    }
    throw new FlywayException(errorMessage);
  }
  LOG.debug("Loading config file: " + configFile.getAbsolutePath());
  try {
    String contents=FileCopyUtils.copyToString(new InputStreamReader(new FileInputStream(configFile),encoding));
    Properties properties=new Properties();
    properties.load(new StringReader(contents.replace("\\","\\\\")));
    return propertiesToMap(properties);
  }
 catch (  IOException e) {
    throw new FlywayException(errorMessage,e);
  }
}
