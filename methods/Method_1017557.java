public List<Method> filter(List<Method> methods){
  List<Method> annotatedCandidates=new ArrayList<Method>();
  List<Method> fallbackCandidates=new ArrayList<Method>();
  for (  Method method : methods) {
    if (method.isBridge()) {
      continue;
    }
    if (this.requiresReply && method.getReturnType().equals(void.class)) {
      continue;
    }
    if (StringUtils.hasText(this.methodName) && !this.methodName.equals(method.getName())) {
      continue;
    }
    if (this.annotationType != null && AnnotationUtils.findAnnotation(method,this.annotationType) != null) {
      annotatedCandidates.add(method);
    }
 else {
      fallbackCandidates.add(method);
    }
  }
  return (!annotatedCandidates.isEmpty()) ? annotatedCandidates : fallbackCandidates;
}
