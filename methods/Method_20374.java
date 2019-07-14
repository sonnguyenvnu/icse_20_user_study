private MethodSpec buildConstructor(ControllerClassInfo controllerInfo){
  ParameterSpec controllerParam=ParameterSpec.builder(controllerInfo.getControllerClassType(),"controller").build();
  return MethodSpec.constructorBuilder().addParameter(controllerParam).addModifiers(Modifier.PUBLIC).addStatement("this.controller = controller").build();
}
