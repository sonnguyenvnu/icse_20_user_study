public static ResourceMapping merge(Class<?> type,ResourceMapping mapping){
  ResourceMapping defaultMapping=new ResourceMapping(findRel(type),findPath(type),findExported(type));
  if (null != mapping) {
    return new ResourceMapping((null != mapping.getRel() ? mapping.getRel() : defaultMapping.getRel()),(null != mapping.getPath() ? mapping.getPath() : defaultMapping.getPath()),(mapping.isExported() != defaultMapping.isExported() ? mapping.isExported() : defaultMapping.isExported())).addResourceMappings(mapping.getResourceMappings());
  }
  return defaultMapping;
}
