private void publishAsNuxeoExtensionPoint(Class<?> beanClass) throws Exception {
  Assert.notNull(beanClass,"Service must be implement!");
  ExtensionPointBuilder extensionPointBuilder=ExtensionPointBuilder.genericExtensionPoint(this.name,applicationContext.getClassLoader());
  if (this.contribution != null && this.contribution.length != 0) {
    for (int i=0; i < contribution.length; i++) {
      extensionPointBuilder.addContribution(contribution[i]);
    }
  }
  Assert.hasLength(beanName,"required property 'beanName' has not been set for creating implementation");
  Assert.notNull(applicationContext,"required property 'applicationContext' has not been set for creating implementation");
  Implementation implementation=new SpringImplementationImpl(targetBeanName,applicationContext);
  ComponentInfo extensionPointComponent=new ExtensionPointComponent(extensionPointBuilder.getExtensionPoint(),sofaRuntimeContext,implementation);
  sofaRuntimeContext.getComponentManager().register(extensionPointComponent);
}
