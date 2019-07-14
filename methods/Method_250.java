private void parseResourceFont(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  if (!TYPEFACE_TYPE.equals(element.asType().toString())) {
    error(element,"@%s field type must be 'Typeface'. (%s.%s)",BindFont.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  hasError|=isInaccessibleViaGeneratedCode(BindFont.class,"fields",element);
  hasError|=isBindingInWrongPackage(BindFont.class,element);
  String name=element.getSimpleName().toString();
  BindFont bindFont=element.getAnnotation(BindFont.class);
  int styleValue=bindFont.style();
  TypefaceStyles style=TypefaceStyles.fromValue(styleValue);
  if (style == null) {
    error(element,"@%s style must be NORMAL, BOLD, ITALIC, or BOLD_ITALIC. (%s.%s)",BindFont.class.getSimpleName(),enclosingElement.getQualifiedName(),name);
    hasError=true;
  }
  if (hasError) {
    return;
  }
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  Id resourceId=elementToId(element,BindFont.class,bindFont.value());
  builder.addResource(new FieldTypefaceBinding(resourceId,name,style));
  erasedTargetNames.add(enclosingElement);
}
