private void addMethodBindings(MethodSpec.Builder result,ViewBinding binding,boolean debuggable){
  Map<ListenerClass,Map<ListenerMethod,Set<MethodViewBinding>>> classMethodBindings=binding.getMethodBindings();
  if (classMethodBindings.isEmpty()) {
    return;
  }
  boolean needsNullChecked=binding.getRequiredBindings().isEmpty();
  if (needsNullChecked) {
    result.beginControlFlow("if (view != null)");
  }
  String fieldName="viewSource";
  String bindName="source";
  if (!binding.isBoundToRoot()) {
    fieldName="view" + Integer.toHexString(binding.getId().value);
    bindName="view";
  }
  result.addStatement("$L = $N",fieldName,bindName);
  for (  Map.Entry<ListenerClass,Map<ListenerMethod,Set<MethodViewBinding>>> e : classMethodBindings.entrySet()) {
    ListenerClass listener=e.getKey();
    Map<ListenerMethod,Set<MethodViewBinding>> methodBindings=e.getValue();
    TypeSpec.Builder callback=TypeSpec.anonymousClassBuilder("").superclass(ClassName.bestGuess(listener.type()));
    for (    ListenerMethod method : getListenerMethods(listener)) {
      MethodSpec.Builder callbackMethod=MethodSpec.methodBuilder(method.name()).addAnnotation(Override.class).addModifiers(PUBLIC).returns(bestGuess(method.returnType()));
      String[] parameterTypes=method.parameters();
      for (int i=0, count=parameterTypes.length; i < count; i++) {
        callbackMethod.addParameter(bestGuess(parameterTypes[i]),"p" + i);
      }
      boolean hasReturnValue=false;
      CodeBlock.Builder builder=CodeBlock.builder();
      Set<MethodViewBinding> methodViewBindings=methodBindings.get(method);
      if (methodViewBindings != null) {
        for (        MethodViewBinding methodBinding : methodViewBindings) {
          if (methodBinding.hasReturnValue()) {
            hasReturnValue=true;
            builder.add("return ");
          }
          builder.add("target.$L(",methodBinding.getName());
          List<Parameter> parameters=methodBinding.getParameters();
          String[] listenerParameters=method.parameters();
          for (int i=0, count=parameters.size(); i < count; i++) {
            if (i > 0) {
              builder.add(", ");
            }
            Parameter parameter=parameters.get(i);
            int listenerPosition=parameter.getListenerPosition();
            if (parameter.requiresCast(listenerParameters[listenerPosition])) {
              if (debuggable) {
                builder.add("$T.castParam(p$L, $S, $L, $S, $L, $T.class)",UTILS,listenerPosition,method.name(),listenerPosition,methodBinding.getName(),i,parameter.getType());
              }
 else {
                builder.add("($T) p$L",parameter.getType(),listenerPosition);
              }
            }
 else {
              builder.add("p$L",listenerPosition);
            }
          }
          builder.add(");\n");
        }
      }
      if (!"void".equals(method.returnType()) && !hasReturnValue) {
        builder.add("return $L;\n",method.defaultReturn());
      }
      callbackMethod.addCode(builder.build());
      callback.addMethod(callbackMethod.build());
    }
    boolean requiresRemoval=listener.remover().length() != 0;
    String listenerField=null;
    if (requiresRemoval) {
      TypeName listenerClassName=bestGuess(listener.type());
      listenerField=fieldName + ((ClassName)listenerClassName).simpleName();
      result.addStatement("$L = $L",listenerField,callback.build());
    }
    String targetType=listener.targetType();
    if (!VIEW_TYPE.equals(targetType)) {
      result.addStatement("(($T) $N).$L($L)",bestGuess(targetType),bindName,listener.setter(),requiresRemoval ? listenerField : callback.build());
    }
 else {
      result.addStatement("$N.$L($L)",bindName,listener.setter(),requiresRemoval ? listenerField : callback.build());
    }
  }
  if (needsNullChecked) {
    result.endControlFlow();
  }
}
