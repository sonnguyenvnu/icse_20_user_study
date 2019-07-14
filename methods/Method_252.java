private void parseResourceString(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (!STRING_TYPE.equals(element.asType().toString())) {
    error(element,"@%s field type must be 'String'. (%s.%s)",BindString.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindString.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindString.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindString.class).value();
  Id resourceId=elementToId(element,BindString.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldResourceBinding(resourceId,name,FieldResourceBinding.Type.STRING));
  erasedTargetNames.add(enclosingElement);
}
