private void parseResourceColor(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  boolean isColorStateList=false;
  TypeMirror elementType=element.asType();
  if (COLOR_STATE_LIST_TYPE.equals(elementType.toString())) {
    isColorStateList=true;
  }
 else   if (elementType.getKind() != TypeKind.INT) {
    error(element,"@%s field type must be 'int' or 'ColorStateList'. (%s.%s)",BindColor.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindColor.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindColor.class,element);
  if (hasError) {
    return;
  }
  String name=element.getSimpleName().toString();
  int id=element.getAnnotation(BindColor.class).value();
  Id resourceId=elementToId(element,BindColor.class,id);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  FieldResourceBinding.Type colorStateList=FieldResourceBinding.Type.COLOR_STATE_LIST;
  FieldResourceBinding.Type color=FieldResourceBinding.Type.COLOR;
  builder.addResource(new FieldResourceBinding(resourceId,name,isColorStateList ? colorStateList : color));
  erasedTargetNames.add(enclosingElement);
}
