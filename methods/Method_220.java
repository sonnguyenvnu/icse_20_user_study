private void addFieldAndUnbindStatement(TypeSpec.Builder result,MethodSpec.Builder unbindMethod,ViewBinding bindings){
  Map<ListenerClass,Map<ListenerMethod,Set<MethodViewBinding>>> classMethodBindings=bindings.getMethodBindings();
  if (classMethodBindings.isEmpty()) {
    return;
  }
  String fieldName=bindings.isBoundToRoot() ? "viewSource" : "view" + Integer.toHexString(bindings.getId().value);
  result.addField(VIEW,fieldName,PRIVATE);
  boolean needsNullChecked=bindings.getRequiredBindings().isEmpty();
  if (needsNullChecked) {
    unbindMethod.beginControlFlow("if ($N != null)",fieldName);
  }
  for (  ListenerClass listenerClass : classMethodBindings.keySet()) {
    boolean requiresRemoval=!"".equals(listenerClass.remover());
    String listenerField="null";
    if (requiresRemoval) {
      TypeName listenerClassName=bestGuess(listenerClass.type());
      listenerField=fieldName + ((ClassName)listenerClassName).simpleName();
      result.addField(listenerClassName,listenerField,PRIVATE);
    }
    String targetType=listenerClass.targetType();
    if (!VIEW_TYPE.equals(targetType)) {
      unbindMethod.addStatement("(($T) $N).$N($N)",bestGuess(targetType),fieldName,removerOrSetter(listenerClass,requiresRemoval),listenerField);
    }
 else {
      unbindMethod.addStatement("$N.$N($N)",fieldName,removerOrSetter(listenerClass,requiresRemoval),listenerField);
    }
    if (requiresRemoval) {
      unbindMethod.addStatement("$N = null",listenerField);
    }
  }
  unbindMethod.addStatement("$N = null",fieldName);
  if (needsNullChecked) {
    unbindMethod.endControlFlow();
  }
}
