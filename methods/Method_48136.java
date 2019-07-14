/** 
 * Load a properties file containing a JanusGraph graph configuration. <p> <ol> <li>Load the file contents into a  {@link org.apache.commons.configuration.PropertiesConfiguration}</li> <li>For each key that points to a configuration object that is either a directory or local file, check whether the associated value is a non-null, non-absolute path. If so, then prepend the absolute path of the parent directory of the provided configuration  {@code file}. This has the effect of making non-absolute backend paths relative to the config file's directory rather than the JVM's working directory. <li>Return the  {@link ReadConfiguration} for the prepared configuration file</li></ol> <p>
 * @param file A properties file to load
 * @return A configuration derived from {@code file}
 */
private static ReadConfiguration getLocalConfiguration(File file){
  Preconditions.checkArgument(file != null && file.exists() && file.isFile() && file.canRead(),"Need to specify a readable configuration file, but was given: %s",file.toString());
  try {
    PropertiesConfiguration configuration=new PropertiesConfiguration(file);
    final File tmpParent=file.getParentFile();
    final File configParent;
    if (null == tmpParent) {
      configParent=new File(System.getProperty("user.dir"));
    }
 else {
      configParent=tmpParent;
    }
    Preconditions.checkNotNull(configParent);
    Preconditions.checkArgument(configParent.isDirectory());
    final Pattern p=Pattern.compile("(" + Pattern.quote(STORAGE_NS.getName()) + "\\..*" + "(" + Pattern.quote(STORAGE_DIRECTORY.getName()) + "|" + Pattern.quote(STORAGE_CONF_FILE.getName()) + ")" + "|" + Pattern.quote(INDEX_NS.getName()) + "\\..*" + "(" + Pattern.quote(INDEX_DIRECTORY.getName()) + "|" + Pattern.quote(INDEX_CONF_FILE.getName()) + ")" + ")");
    final Iterator<String> keysToMangle=Iterators.filter(configuration.getKeys(),key -> null != key && p.matcher(key).matches());
    while (keysToMangle.hasNext()) {
      String k=keysToMangle.next();
      Preconditions.checkNotNull(k);
      final String s=configuration.getString(k);
      Preconditions.checkArgument(StringUtils.isNotBlank(s),"Invalid Configuration: key %s has null empty value",k);
      configuration.setProperty(k,getAbsolutePath(configParent,s));
    }
    return new CommonsConfiguration(configuration);
  }
 catch (  ConfigurationException e) {
    throw new IllegalArgumentException("Could not load configuration at: " + file,e);
  }
}
