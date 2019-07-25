@VisibleForTesting static ServiceSpecification generate(Map<Class<?>,String> serviceDescription,Map<Class<?>,Set<MethodInfo>> methodInfos){
  final Set<ServiceInfo> serviceInfos=methodInfos.entrySet().stream().map(entry -> {
    final Class<?> service=entry.getKey();
    return new ServiceInfo(service.getName(),entry.getValue(),serviceDescription.get(service));
  }
).collect(toImmutableSet());
  return ServiceSpecification.generate(serviceInfos,AnnotatedHttpDocServicePlugin::newNamedTypeInfo);
}
