@NonNull public Element fromDelegateConstructor(@NonNull ExecutableElement constructor,boolean hasContextParameter){
  return new BuilderElement.Delegate(TypeName.get(constructor.getEnclosingElement().asType()),hasContextParameter);
}
