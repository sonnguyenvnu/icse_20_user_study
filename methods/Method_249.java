private void parseResourceFloat(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (element.asType().getKind() != TypeKind.FLOAT) {
    error(element,"@%s field type must be 'float'. (%s.%s)",BindFloat.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindFloat.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindFloat.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindFloat.class).value();
  Id resourceId=elementToId(element,BindFloat.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldResourceBinding(resourceId,name,FieldResourceBinding.Type.FLOAT));
  erasedTargetNames.add(enclosingElement);
}
