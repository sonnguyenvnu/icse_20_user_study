/** 
 * Convert child data to attribute.
 * @param overridePath    the override path
 * @param childData       the child data
 * @param removeType      is remove type
 * @param interfaceConfig register provider/consumer config
 * @return the attribute
 * @throws Exception decode exception
 */
static Map<String,String> convertOverrideToAttribute(String overridePath,ChildData childData,boolean removeType,AbstractInterfaceConfig interfaceConfig) throws Exception {
  String url=URLDecoder.decode(childData.getPath().substring(overridePath.length() + 1),"UTF-8");
  Map<String,String> attribute=new ConcurrentHashMap<String,String>();
  for (  String keyPairs : url.substring(url.indexOf('?') + 1).split("&")) {
    String[] overrideAttrs=keyPairs.split("=");
    List<String> configKeys=Arrays.asList(RpcConstants.CONFIG_KEY_TIMEOUT,RpcConstants.CONFIG_KEY_SERIALIZATION,RpcConstants.CONFIG_KEY_LOADBALANCER);
    if (configKeys.contains(overrideAttrs[0])) {
      if (removeType) {
        Class clazz=null;
        if (interfaceConfig instanceof ProviderConfig) {
          clazz=ProviderConfig.class;
        }
 else         if (interfaceConfig instanceof ConsumerConfig) {
          clazz=ConsumerConfig.class;
        }
        if (clazz != null) {
          Method getMethod=ReflectUtils.getPropertyGetterMethod(clazz,overrideAttrs[0]);
          Class propertyClazz=getMethod.getReturnType();
          attribute.put(overrideAttrs[0],StringUtils.toString(BeanUtils.getProperty(interfaceConfig,overrideAttrs[0],propertyClazz)));
        }
      }
 else {
        attribute.put(overrideAttrs[0],overrideAttrs[1]);
      }
    }
  }
  return attribute;
}
