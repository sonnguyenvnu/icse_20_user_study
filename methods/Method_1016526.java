@Override public Optional<TypeInfo> find(String name){
  TypeElement element=elements.getTypeElement(name);
  if (element == null) {
    return Optional.empty();
  }
  return Optional.of(new CompilerReflection.ElementsTypeInfo(elements,element));
}
