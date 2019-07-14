static TypeSpecDataHolder generateOnStateUpdateMethod(SpecModel specModel,SpecMethodModel<UpdateStateMethod,Void> updateStateMethod,StateUpdateType stateUpdateType,int index){
  final String name;
switch (stateUpdateType) {
case DEFAULT:
    name=updateStateMethod.name.toString();
  break;
case ASYNC:
name=updateStateMethod.name.toString() + "Async";
break;
case SYNC:
name=updateStateMethod.name.toString() + "Sync";
break;
case WITH_TRANSITION:
name=updateStateMethod.name.toString() + "WithTransition";
break;
default :
throw new RuntimeException("Unhandled state update type: " + stateUpdateType);
}
final MethodSpec.Builder builder=MethodSpec.methodBuilder(name).addModifiers(Modifier.PROTECTED,Modifier.STATIC).addParameter(specModel.getContextClass(),"c");
builder.addStatement("$T _component = c.get$LScope()",specModel.getComponentClass(),specModel.getComponentClass().simpleName()).addCode(CodeBlock.builder().beginControlFlow("if (_component == null)").addStatement("return").endControlFlow().build());
final CodeBlock.Builder codeBlockBuilder=CodeBlock.builder();
codeBlockBuilder.add("$T _stateUpdate = new $T($L",ClassNames.COMPONENT_STATE_UPDATE,ClassNames.COMPONENT_STATE_UPDATE,index);
for (MethodParamModel methodParam : updateStateMethod.methodParams) {
if (MethodParamModelUtils.isAnnotatedWith(methodParam,Param.class)) {
final String paramName=methodParam.getName();
builder.addParameter(methodParam.getTypeName(),paramName).addTypeVariables(MethodParamModelUtils.getTypeVariables(methodParam));
codeBlockBuilder.add(", ").add(paramName);
}
}
codeBlockBuilder.add(");\n");
builder.addCode(codeBlockBuilder.build());
final String stateUpdateAttribution='"' + specModel.getComponentName() + "." + updateStateMethod.name.toString() + '"';
final String stateUpdateMethod;
switch (stateUpdateType) {
case SYNC:
stateUpdateMethod="updateStateSync";
break;
case DEFAULT:
case ASYNC:
stateUpdateMethod="updateStateAsync";
break;
case WITH_TRANSITION:
stateUpdateMethod="updateStateWithTransition";
break;
default :
throw new RuntimeException("Unhandled state update type: " + stateUpdateType);
}
builder.addStatement("c." + stateUpdateMethod + "(_stateUpdate, " + stateUpdateAttribution + ")");
return TypeSpecDataHolder.newBuilder().addMethod(builder.build()).build();
}
