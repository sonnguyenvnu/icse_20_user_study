public static TypeClass from(TypeElement typeElement){
  return new TypeClass(QualifiedName.of(typeElement),typeElement.getTypeParameters());
}
