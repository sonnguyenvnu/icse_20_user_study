/** 
 * Copy from {@link BusEnvironmentPostProcessor#addOrReplace(MutablePropertySources,Map)}
 * @param propertySources {@link MutablePropertySources}
 * @param map Default RocketMQ Bus Properties
 */
private void addOrReplace(MutablePropertySources propertySources,Map<String,Object> map){
  MapPropertySource target=null;
  if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
    PropertySource<?> source=propertySources.get(PROPERTY_SOURCE_NAME);
    if (source instanceof MapPropertySource) {
      target=(MapPropertySource)source;
      for (      String key : map.keySet()) {
        if (!target.containsProperty(key)) {
          target.getSource().put(key,map.get(key));
        }
      }
    }
  }
  if (target == null) {
    target=new MapPropertySource(PROPERTY_SOURCE_NAME,map);
  }
  if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
    propertySources.addLast(target);
  }
}
