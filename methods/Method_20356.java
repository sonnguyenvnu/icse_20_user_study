boolean implicitlyAddAutoModels(ControllerClassInfo controller){
  return globalImplicitlyAddAutoModels || getConfigurationForElement(controller.getControllerClassElement()).getImplicitlyAddAutoModels();
}
