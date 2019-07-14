private void parseResourceAnimation(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (!ANIMATION_TYPE.equals(element.asType().toString())) {
    error(element,"@%s field type must be 'Animation'. (%s.%s)",BindAnim.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindAnim.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindAnim.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindAnim.class).value();
  Id resourceId=elementToId(element,BindAnim.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addResource(new FieldAnimationBinding(resourceId,name));
  erasedTargetNames.add(enclosingElement);
}
