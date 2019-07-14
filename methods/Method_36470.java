@Override public void publishExtension(ExtensionParam extensionParam){
  Assert.notNull(extensionParam,"extensionParam can not be null.");
  Assert.notNull(extensionParam.getElement(),"Extension contribution element can not be null.");
  Assert.notNull(extensionParam.getTargetInstanceName(),"Extension target instance name can not be null.");
  Assert.notNull(extensionParam.getTargetName(),"Extension target name can not be null.");
  ExtensionImpl extension=new ExtensionImpl(null,extensionParam.getTargetName(),extensionParam.getElement(),sofaRuntimeContext.getAppClassLoader());
  extension.setTargetComponentName(ComponentNameFactory.createComponentName(ExtensionPointComponent.EXTENSION_POINT_COMPONENT_TYPE,extensionParam.getTargetInstanceName() + ExtensionComponent.LINK_SYMBOL + extensionParam.getTargetName()));
  ComponentInfo extensionComponent=new ExtensionComponent(extension,sofaRuntimeContext);
  sofaRuntimeContext.getComponentManager().register(extensionComponent);
}
