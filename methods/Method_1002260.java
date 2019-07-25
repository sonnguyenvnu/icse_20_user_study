/** 
 * Initialize the DynamicProperty capability by installing a configuration listener.
 */
static synchronized void initialize(DynamicPropertySupport config){
  dynamicPropertySupportImpl=config;
  config.addConfigurationListener(new DynamicPropertyListener());
  updateAllProperties();
}
