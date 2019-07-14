private void parseBindView(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  boolean hasError=isInaccessibleViaGeneratedCode(BindView.class,"fields",element) || isBindingInWrongPackage(BindView.class,element);
  TypeMirror elementType=element.asType();
  if (elementType.getKind() == TypeKind.TYPEVAR) {
    TypeVariable typeVariable=(TypeVariable)elementType;
    elementType=typeVariable.getUpperBound();
  }
  Name qualifiedName=enclosingElement.getQualifiedName();
  Name simpleName=element.getSimpleName();
  if (!isSubtypeOfType(elementType,VIEW_TYPE) && !isInterface(elementType)) {
    if (elementType.getKind() == TypeKind.ERROR) {
      note(element,"@%s field with unresolved type (%s) " + "must elsewhere be generated as a View or interface. (%s.%s)",BindView.class.getSimpleName(),elementType,qualifiedName,simpleName);
    }
 else {
      error(element,"@%s fields must extend from View or be an interface. (%s.%s)",BindView.class.getSimpleName(),qualifiedName,simpleName);
      hasError=true;
    }
  }
  if (hasError) {
    return;
  }
  int id=element.getAnnotation(BindView.class).value();
  BindingSet.Builder builder=builderMap.get(enclosingElement);
  Id resourceId=elementToId(element,BindView.class,id);
  if (builder != null) {
    String existingBindingName=builder.findExistingBindingName(resourceId);
    if (existingBindingName != null) {
      error(element,"Attempt to use @%s for an already bound ID %d on '%s'. (%s.%s)",BindView.class.getSimpleName(),id,existingBindingName,enclosingElement.getQualifiedName(),element.getSimpleName());
      return;
    }
  }
 else {
    builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  }
  String name=simpleName.toString();
  TypeName type=TypeName.get(elementType);
  boolean required=isFieldRequired(element);
  builder.addField(resourceId,new FieldViewBinding(name,type,required));
  erasedTargetNames.add(enclosingElement);
}
