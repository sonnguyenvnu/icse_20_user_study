@Override public boolean process(Set<? extends TypeElement> annotations,RoundEnvironment roundEnv){
  for (  Element element : roundEnv.getRootElements()) {
    if (element instanceof TypeElement) {
      TypeElement typeElement=(TypeElement)element;
      if (DATA_BINDER_MAPPER_FULL_NAME.contentEquals(typeElement.getQualifiedName())) {
        readDataBinderMapper();
      }
    }
  }
  return false;
}
