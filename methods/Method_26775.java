private static Map<String,Object> annotationKeyValues(AnnotationMirror mirror){
  Map<String,Object> result=new LinkedHashMap<>();
  for (  ExecutableElement key : mirror.getElementValues().keySet()) {
    result.put(key.getSimpleName().toString(),mirror.getElementValues().get(key).getValue());
  }
  return result;
}
