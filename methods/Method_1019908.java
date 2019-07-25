@Override public boolean applicable(Context context){
  TypeElement type=context.autoValueClass();
  TypeName typeName=TypeName.get(type.asType());
  ParameterizedTypeName typeAdapterType=ParameterizedTypeName.get(ClassName.get(TypeAdapter.class),typeName);
  boolean generateExternalAdapter=type.getAnnotation(GenerateTypeAdapter.class) != null;
  TypeName returnedTypeAdapter=null;
  for (  ExecutableElement method : ElementFilter.methodsIn(type.getEnclosedElements())) {
    if (method.getModifiers().contains(STATIC) && !method.getModifiers().contains(PRIVATE)) {
      TypeMirror rType=method.getReturnType();
      TypeName returnType=TypeName.get(rType);
      if (returnType.equals(typeAdapterType)) {
        return true;
      }
      if (returnType.equals(typeAdapterType.rawType) || (returnType instanceof ParameterizedTypeName && ((ParameterizedTypeName)returnType).rawType.equals(typeAdapterType.rawType))) {
        returnedTypeAdapter=returnType;
      }
    }
  }
  if (returnedTypeAdapter == null && !generateExternalAdapter) {
    return false;
  }
  if (returnedTypeAdapter != null) {
    Messager messager=context.processingEnvironment().getMessager();
    if (returnedTypeAdapter instanceof ParameterizedTypeName) {
      ParameterizedTypeName paramReturnType=(ParameterizedTypeName)returnedTypeAdapter;
      TypeName argument=paramReturnType.typeArguments.get(0);
      if (typeName instanceof ParameterizedTypeName) {
        ParameterizedTypeName pTypeName=(ParameterizedTypeName)typeName;
        return pTypeName.rawType.equals(argument);
      }
 else {
        messager.printMessage(Diagnostic.Kind.WARNING,String.format("Found static method returning TypeAdapter<%s> on %s class. " + "Skipping GsonTypeAdapter generation.",argument,type));
      }
    }
 else {
      messager.printMessage(Diagnostic.Kind.WARNING,"Found static method returning " + "TypeAdapter with no type arguments, skipping GsonTypeAdapter generation.");
    }
    return false;
  }
 else {
    return true;
  }
}
