private void parseResourceBitmap(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (!BITMAP_TYPE.equals(element.asType().toString())) {
    error(element,"@%s field type must be 'Bitmap'. (%s.%s)",BindBitmap.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindBitmap.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindBitmap.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindBitmap.class).value();
  Id resourceId=elementToId(element,BindBitmap.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldResourceBinding(resourceId,name,FieldResourceBinding.Type.BITMAP));
  erasedTargetNames.add(enclosingElement);
}
