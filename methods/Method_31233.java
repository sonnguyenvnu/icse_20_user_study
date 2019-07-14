/** 
 * Dumps the configuration to the console when debug output is activated.
 * @param config The configured properties.
 */
public static void dumpConfiguration(Map<String,String> config){
  if (LOG.isDebugEnabled()) {
    LOG.debug("Using configuration:");
    for (    Map.Entry<String,String> entry : new TreeMap<>(config).entrySet()) {
      String value=entry.getValue();
      value=ConfigUtils.PASSWORD.equals(entry.getKey()) ? StringUtils.trimOrPad("",value.length(),'*') : value;
      LOG.debug(entry.getKey() + " -> " + value);
    }
  }
}
