private Set<Class<?>> init(@Nullable Object implementation,Iterable<Class<?>> candidateInterfaces){
  final Set<String> methodNames=new HashSet<>();
  final Set<Class<?>> interfaces=new HashSet<>();
  for (  Class<?> iface : candidateInterfaces) {
    final Map<String,AsyncProcessFunction<?,?,?>> asyncProcessMap;
    asyncProcessMap=getThriftAsyncProcessMap(implementation,iface);
    if (asyncProcessMap != null) {
      asyncProcessMap.forEach((name,func) -> registerFunction(methodNames,iface,name,func));
      interfaces.add(iface);
    }
    final Map<String,ProcessFunction<?,?>> processMap;
    processMap=getThriftProcessMap(implementation,iface);
    if (processMap != null) {
      processMap.forEach((name,func) -> registerFunction(methodNames,iface,name,func));
      interfaces.add(iface);
    }
  }
  if (functions.isEmpty()) {
    if (implementation != null) {
      throw new IllegalArgumentException('\'' + implementation.getClass().getName() + "' is not a Thrift service implementation.");
    }
 else {
      throw new IllegalArgumentException("not a Thrift service interface: " + candidateInterfaces);
    }
  }
  return Collections.unmodifiableSet(interfaces);
}
