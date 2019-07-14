/** 
 * Registers or defines a bean.
 * @param type bean type, must be specified
 * @param name bean name, if <code>null</code> it will be resolved from the class (name or annotation)
 * @param scopeType bean scope, if <code>null</code> it will be resolved from the class (annotation or default one)
 * @param wiringMode wiring mode, if <code>null</code> it will be resolved from the class (annotation or default one)
 * @param define when set to <code>true</code> bean will be defined - all injection points will be set to none
 */
public <T>BeanDefinition<T> registerPetiteBean(final Class<T> type,String name,Class<? extends Scope> scopeType,WiringMode wiringMode,final boolean define,final Consumer<T> consumer){
  if (name == null) {
    name=resolveBeanName(type);
  }
  if (wiringMode == null) {
    wiringMode=annotationResolver.resolveBeanWiringMode(type);
  }
  if (wiringMode == WiringMode.DEFAULT) {
    wiringMode=petiteConfig.getDefaultWiringMode();
  }
  if (scopeType == null) {
    scopeType=annotationResolver.resolveBeanScopeType(type);
  }
  if (scopeType == null) {
    scopeType=SingletonScope.class;
  }
  BeanDefinition existing=removeBean(name);
  if (existing != null) {
    if (petiteConfig.getDetectDuplicatedBeanNames()) {
      throw new PetiteException("Duplicated bean name detected while registering class '" + type.getName() + "'. Petite bean class '" + existing.type.getName() + "' is already registered with the name: " + name);
    }
  }
  if (type.isInterface()) {
    throw new PetiteException("PetiteBean can not be an interface: " + type.getName());
  }
  if (log.isDebugEnabled()) {
    log.info("Petite bean: [" + name + "] --> " + type.getName() + " @ " + scopeType.getSimpleName() + ":" + wiringMode.toString());
  }
  Scope scope=resolveScope(scopeType);
  BeanDefinition<T> beanDefinition=createBeanDefinitionForRegistration(name,type,scope,wiringMode,consumer);
  registerBean(name,beanDefinition);
  ProviderDefinition[] providerDefinitions=petiteResolvers.resolveProviderDefinitions(type,name);
  if (providerDefinitions != null) {
    for (    ProviderDefinition providerDefinition : providerDefinitions) {
      providers.put(providerDefinition.name,providerDefinition);
    }
  }
  if (define) {
    beanDefinition.ctor=petiteResolvers.resolveCtorInjectionPoint(beanDefinition.type());
    beanDefinition.properties=PropertyInjectionPoint.EMPTY;
    beanDefinition.methods=MethodInjectionPoint.EMPTY;
    beanDefinition.initMethods=InitMethodPoint.EMPTY;
    beanDefinition.destroyMethods=DestroyMethodPoint.EMPTY;
  }
  return beanDefinition;
}
