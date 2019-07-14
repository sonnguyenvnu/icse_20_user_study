@SuppressWarnings("unchecked") protected String[] transformParameterNames(String[] parameterNames,Class<?>[] parameterType,Annotation[][] annotations){
  for (int i=0; i < annotations.length; ++i) {
    for (    Annotation annotation : annotations[i]) {
      if (annotation instanceof SofaReference) {
        AnnotationWrapperBuilder<SofaReference> wrapperBuilder=AnnotationWrapperBuilder.wrap(annotation).withBinder(binder);
        SofaReference delegate=wrapperBuilder.build();
        Class interfaceType=delegate.interfaceType();
        if (interfaceType.equals(void.class)) {
          interfaceType=parameterType[i];
        }
        String uniqueId=delegate.uniqueId();
        parameterNames[i]=SofaBeanNameGenerator.generateSofaReferenceBeanName(interfaceType,uniqueId);
      }
    }
  }
  return parameterNames;
}
