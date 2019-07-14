@NonNull public Element fromBuilderDelegateMethod(@NonNull ExecutableElement method){
  return new DelegateMethod(method.getSimpleName().toString(),TypeName.get(method.getReturnType()),getAnnotations(method).getLeft(),method.getParameters().stream().map(p -> ParameterSpec.builder(TypeName.get(p.asType()),p.getSimpleName().toString()).build()).collect(Collectors.toList()),method.getTypeParameters().stream().map(TypeVariableName::get).collect(Collectors.toList()),method.getModifiers(),elements.getDocComment(method));
}
