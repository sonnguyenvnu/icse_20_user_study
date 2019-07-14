private void parseResourceBool(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (element.asType().getKind() != TypeKind.BOOLEAN) {
    error(element,"@%s field type must be 'boolean'. (%s.%s)",BindBool.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindBool.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindBool.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindBool.class).value();
  Id resourceId=elementToId(element,BindBool.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldResourceBinding(resourceId,name,FieldResourceBinding.Type.BOOL));
  erasedTargetNames.add(enclosingElement);
}
