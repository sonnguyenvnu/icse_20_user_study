private void publishAsNuxeoExtension() throws Exception {
  ExtensionBuilder extensionBuilder=ExtensionBuilder.genericExtension(this.getPoint(),this.getContent(),applicationContext,classLoader);
  extensionBuilder.setExtensionPoint(getExtensionPointComponentName());
  ComponentInfo componentInfo=new ExtensionComponent(extensionBuilder.getExtension(),sofaRuntimeContext);
  sofaRuntimeContext.getComponentManager().register(componentInfo);
}
