protected Object lookupMixingScopedBean(final BeanDefinition def,final BeanReferences refNames){
  final boolean mixing=petiteConfig.wireScopedProxy || petiteConfig.detectMixedScopes;
  Object value=null;
  if (mixing) {
    final BeanDefinition refBeanDefinition=lookupBeanDefinitions(refNames);
    if (refBeanDefinition != null) {
      value=scopedProxyManager.lookupValue(PetiteContainer.this,def,refBeanDefinition);
    }
  }
  if (value == null) {
    value=PetiteContainer.this.getBean(refNames);
  }
  return value;
}
