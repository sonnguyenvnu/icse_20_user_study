/** 
 * Resolves all providers in the class
 */
public ProviderDefinition[] resolve(final Class type,final String name){
  ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  MethodDescriptor[] methods=cd.getAllMethodDescriptors();
  List<ProviderDefinition> list=new ArrayList<>();
  for (  MethodDescriptor methodDescriptor : methods) {
    Method method=methodDescriptor.getMethod();
    PetiteProvider petiteProvider=method.getAnnotation(PetiteProvider.class);
    if (petiteProvider == null) {
      continue;
    }
    String providerName=petiteProvider.value();
    if (StringUtil.isBlank(providerName)) {
      providerName=method.getName();
      if (providerName.endsWith("Provider")) {
        providerName=StringUtil.substring(providerName,0,-8);
      }
    }
    ProviderDefinition providerDefinition;
    if (Modifier.isStatic(method.getModifiers())) {
      providerDefinition=new ProviderDefinition(providerName,method);
    }
 else {
      providerDefinition=new ProviderDefinition(providerName,name,method);
    }
    list.add(providerDefinition);
  }
  ProviderDefinition[] providers;
  if (list.isEmpty()) {
    providers=ProviderDefinition.EMPTY;
  }
 else {
    providers=list.toArray(new ProviderDefinition[0]);
  }
  return providers;
}
