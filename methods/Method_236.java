static Builder newBuilder(TypeElement enclosingElement){
  TypeMirror typeMirror=enclosingElement.asType();
  boolean isView=isSubtypeOfType(typeMirror,VIEW_TYPE);
  boolean isActivity=isSubtypeOfType(typeMirror,ACTIVITY_TYPE);
  boolean isDialog=isSubtypeOfType(typeMirror,DIALOG_TYPE);
  TypeName targetType=TypeName.get(typeMirror);
  if (targetType instanceof ParameterizedTypeName) {
    targetType=((ParameterizedTypeName)targetType).rawType;
  }
  String packageName=getPackage(enclosingElement).getQualifiedName().toString();
  String className=enclosingElement.getQualifiedName().toString().substring(packageName.length() + 1).replace('.','$');
  ClassName bindingClassName=ClassName.get(packageName,className + "_ViewBinding");
  boolean isFinal=enclosingElement.getModifiers().contains(Modifier.FINAL);
  return new Builder(targetType,bindingClassName,isFinal,isView,isActivity,isDialog);
}
