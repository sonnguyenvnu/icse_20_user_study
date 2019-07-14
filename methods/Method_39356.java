/** 
 * Resolves bean's name from bean annotation or type name. May be used for resolving bean name of base type during registration of bean subclass.
 */
public String resolveBeanName(final Class type){
  return annotationResolver.resolveBeanName(type,petiteConfig.getUseFullTypeNames());
}
