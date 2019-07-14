private void parseResourceDimen(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  boolean isInt=false;
  TypeMirror elementType=element.asType();
  if (elementType.getKind() == TypeKind.INT) {
    isInt=true;
  }
 else   if (elementType.getKind() != TypeKind.FLOAT) {
    error(element,"@%s field type must be 'int' or 'float'. (%s.%s)",BindDimen.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindDimen.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindDimen.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindDimen.class).value();
  Id resourceId=elementToId(element,BindDimen.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldResourceBinding(resourceId,name,isInt ? FieldResourceBinding.Type.DIMEN_AS_INT : FieldResourceBinding.Type.DIMEN_AS_FLOAT));
  erasedTargetNames.add(enclosingElement);
}
