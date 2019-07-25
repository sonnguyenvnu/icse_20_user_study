public static ResourceMapping merge(Method method,ResourceMapping mapping){
  ResourceMapping defaultMapping=new ResourceMapping(findRel(method),findPath(method),findExported(method));
  if (null != mapping) {
    return new ResourceMapping((null != mapping.getRel() ? mapping.getRel() : defaultMapping.getRel()),(null != mapping.getPath() ? mapping.getPath() : defaultMapping.getPath()),(mapping.isExported() != defaultMapping.isExported() ? mapping.isExported() : defaultMapping.isExported()));
  }
  return defaultMapping;
}
