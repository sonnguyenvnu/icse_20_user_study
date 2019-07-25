@Override public FieldMap build(ClassMap classMap,BeanContainer beanContainer,DestBeanCreator destBeanCreator,PropertyDescriptorFactory propertyDescriptorFactory){
  if (!StringUtils.isBlank(aHint)) {
    setAHint(elEngine.resolve(aHint));
  }
  if (!StringUtils.isBlank(bHint)) {
    setBHint(elEngine.resolve(bHint));
  }
  if (!StringUtils.isBlank(aDeepIndexHint)) {
    setADeepIndexHint(elEngine.resolve(aDeepIndexHint));
  }
  if (!StringUtils.isBlank(bDeepIndexHint)) {
    setBDeepIndexHint(elEngine.resolve(bDeepIndexHint));
  }
  if (!StringUtils.isBlank(mapId)) {
    setMapId(elEngine.resolve(mapId));
  }
  if (!StringUtils.isBlank(customConverter)) {
    setCustomConverter(elEngine.resolve(customConverter));
  }
  if (!StringUtils.isBlank(customConverterId)) {
    setCustomConverterId(elEngine.resolve(customConverterId));
  }
  if (!StringUtils.isBlank(customConverterParam)) {
    setCustomConverterParam(elEngine.resolve(customConverterParam));
  }
  return super.build(classMap,beanContainer,destBeanCreator,propertyDescriptorFactory);
}
