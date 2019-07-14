private void parseResourceInt(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (element.asType().getKind() != TypeKind.INT) {
    error(element,"@%s field type must be 'int'. (%s.%s)",BindInt.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindInt.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindInt.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindInt.class).value();
  Id resourceId=elementToId(element,BindInt.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldResourceBinding(resourceId,name,FieldResourceBinding.Type.INT));
  erasedTargetNames.add(enclosingElement);
}
