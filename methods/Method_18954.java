@Nullable static TypeName getReturnType(Elements elements,Element typeElement){
  TypeMirror typeMirror=ProcessorUtils.getAnnotationParameter(elements,typeElement,Event.class,"returnType",TypeMirror.class);
  return typeMirror != null ? TypeName.get(typeMirror) : null;
}
