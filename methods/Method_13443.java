protected Set<DubboTransportedMethodMetadata> resolveDubboTransportedMethodMetadataSet(Class<?> targetType){
  Method[] methods=targetType.getMethods();
  Set<DubboTransportedMethodMetadata> methodMetadataSet=new LinkedHashSet<>();
  for (  Method method : methods) {
    DubboTransported dubboTransported=resolveDubboTransported(method);
    if (dubboTransported != null) {
      DubboTransportedMethodMetadata methodMetadata=createDubboTransportedMethodMetadata(method,dubboTransported);
      methodMetadataSet.add(methodMetadata);
    }
  }
  return methodMetadataSet;
}
