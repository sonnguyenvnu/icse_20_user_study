private static boolean isFromEventTypeSpecifiedInAnnotation(MethodParamModel methodParamModel,TypeName eventFieldType){
  FromEvent fromEvent=(FromEvent)MethodParamModelUtils.getAnnotation(methodParamModel,FromEvent.class);
  TypeName baseClassType;
  try {
    baseClassType=ClassName.get(fromEvent.baseClass());
  }
 catch (  MirroredTypeException mte) {
    baseClassType=ClassName.get(mte.getTypeMirror());
  }
  return baseClassType.equals(eventFieldType);
}
