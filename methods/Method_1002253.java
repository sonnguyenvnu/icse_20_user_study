/** 
 * Install the system wide configuration with the ConfigurationManager. This will also install  the configuration with the  {@link DynamicPropertyFactory} by calling {@link DynamicPropertyFactory#initWithConfigurationSource(AbstractConfiguration)}. This call can be made only once, otherwise IllegalStateException will be thrown.
 */
public static synchronized void install(AbstractConfiguration config) throws IllegalStateException {
  if (!customConfigurationInstalled) {
    setDirect(config);
    if (DynamicPropertyFactory.getBackingConfigurationSource() != config) {
      DynamicPropertyFactory.initWithConfigurationSource(config);
    }
  }
 else {
    throw new IllegalStateException("A non-default configuration is already installed");
  }
}
