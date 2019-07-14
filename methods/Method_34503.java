/** 
 * Creates and sets Hystrix command properties.
 * @param properties the collapser properties
 */
public static HystrixCommandProperties.Setter initializeCommandProperties(List<HystrixProperty> properties) throws IllegalArgumentException {
  return initializeProperties(HystrixCommandProperties.Setter(),properties,CMD_PROP_MAP,"command");
}
