private void parseResourceDrawable(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (!DRAWABLE_TYPE.equals(element.asType().toString())) {
    error(element,"@%s field type must be 'Drawable'. (%s.%s)",BindDrawable.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindDrawable.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindDrawable.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindDrawable.class).value();
  int tint=element.getAnnotation(BindDrawable.class).tint();
  Map<Integer,Id> resourceIds=elementToIds(element,BindDrawable.class,new int[]{id,tint});
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldDrawableBinding(resourceIds.get(id),name,resourceIds.get(tint)));
  erasedTargetNames.add(enclosingElement);
}
