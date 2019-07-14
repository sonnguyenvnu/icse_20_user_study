private boolean hasSofaServiceAnnotation(){
  Class<?> implementationClazz=ref.getClass();
  SofaService sofaService=implementationClazz.getAnnotation(SofaService.class);
  if (sofaService == null) {
    return false;
  }
  String annotationUniqueId=sofaService.uniqueId();
  if ((uniqueId == null || uniqueId.isEmpty()) && (annotationUniqueId == null || annotationUniqueId.isEmpty())) {
    return true;
  }
  return annotationUniqueId.equals(uniqueId);
}
