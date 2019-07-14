private void parseResourceArray(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  FieldResourceBinding.Type type=getArrayResourceMethodName(element);
  if (type == null) {
    error(element,"@%s field type must be one of: String[], int[], CharSequence[], %s. (%s.%s)",BindArray.class.getSimpleName(),TYPED_ARRAY_TYPE,enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindArray.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindArray.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindArray.class).value();
  Id resourceId=elementToId(element,BindArray.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldResourceBinding(resourceId,name,requireNonNull(type)));
  erasedTargetNames.add(enclosingElement);
}
