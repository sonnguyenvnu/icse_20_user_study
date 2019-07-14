private void parseBindViews(Element element,Map<TypeElement,BindingSet.Builder> builderMap,Set<TypeElement> erasedTargetNames){
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  boolean hasError=isInaccessibleViaGeneratedCode(BindViews.class,"fields",element) || isBindingInWrongPackage(BindViews.class,element);
  TypeMirror elementType=element.asType();
  String erasedType=doubleErasure(elementType);
  TypeMirror viewType=null;
  FieldCollectionViewBinding.Kind kind=null;
  if (elementType.getKind() == TypeKind.ARRAY) {
    ArrayType arrayType=(ArrayType)elementType;
    viewType=arrayType.getComponentType();
    kind=FieldCollectionViewBinding.Kind.ARRAY;
  }
 else   if (LIST_TYPE.equals(erasedType)) {
    DeclaredType declaredType=(DeclaredType)elementType;
    List<? extends TypeMirror> typeArguments=declaredType.getTypeArguments();
    if (typeArguments.size() != 1) {
      error(element,"@%s List must have a generic component. (%s.%s)",BindViews.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
      hasError=true;
    }
 else {
      viewType=typeArguments.get(0);
    }
    kind=FieldCollectionViewBinding.Kind.LIST;
  }
 else {
    error(element,"@%s must be a List or array. (%s.%s)",BindViews.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  if (viewType != null && viewType.getKind() == TypeKind.TYPEVAR) {
    TypeVariable typeVariable=(TypeVariable)viewType;
    viewType=typeVariable.getUpperBound();
  }
  if (viewType != null && !isSubtypeOfType(viewType,VIEW_TYPE) && !isInterface(viewType)) {
    if (viewType.getKind() == TypeKind.ERROR) {
      note(element,"@%s List or array with unresolved type (%s) " + "must elsewhere be generated as a View or interface. (%s.%s)",BindViews.class.getSimpleName(),viewType,enclosingElement.getQualifiedName(),element.getSimpleName());
    }
 else {
      error(element,"@%s List or array type must extend from View or be an interface. (%s.%s)",BindViews.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
      hasError=true;
    }
  }
  String name=element.getSimpleName().toString();
  int[] ids=element.getAnnotation(BindViews.class).value();
  if (ids.length == 0) {
    error(element,"@%s must specify at least one ID. (%s.%s)",BindViews.class.getSimpleName(),enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  Integer duplicateId=findDuplicate(ids);
  if (duplicateId != null) {
    error(element,"@%s annotation contains duplicate ID %d. (%s.%s)",BindViews.class.getSimpleName(),duplicateId,enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  if (hasError) {
    return;
  }
  TypeName type=TypeName.get(requireNonNull(viewType));
  boolean required=isFieldRequired(element);
  BindingSet.Builder builder=getOrCreateBindingBuilder(builderMap,enclosingElement);
  builder.addFieldCollection(new FieldCollectionViewBinding(name,type,requireNonNull(kind),new ArrayList<>(elementToIds(element,BindViews.class,ids).values()),required));
  erasedTargetNames.add(enclosingElement);
}
