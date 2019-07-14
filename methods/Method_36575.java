private void processSofaReference(final Object bean){
  final Class<?> beanClass=bean.getClass();
  ReflectionUtils.doWithFields(beanClass,new ReflectionUtils.FieldCallback(){
    @Override @SuppressWarnings("unchecked") public void doWith(    Field field) throws IllegalArgumentException, IllegalAccessException {
      AnnotationWrapperBuilder<SofaReference> builder=AnnotationWrapperBuilder.wrap(field.getAnnotation(SofaReference.class)).withBinder(binder);
      SofaReference sofaReferenceAnnotation=builder.build();
      if (sofaReferenceAnnotation == null) {
        return;
      }
      Class<?> interfaceType=sofaReferenceAnnotation.interfaceType();
      if (interfaceType.equals(void.class)) {
        interfaceType=field.getType();
      }
      Object proxy=createReferenceProxy(sofaReferenceAnnotation,interfaceType);
      ReflectionUtils.makeAccessible(field);
      ReflectionUtils.setField(field,bean,proxy);
    }
  }
,new ReflectionUtils.FieldFilter(){
    @Override public boolean matches(    Field field){
      return !Modifier.isStatic(field.getModifiers()) && field.isAnnotationPresent(SofaReference.class);
    }
  }
);
  ReflectionUtils.doWithMethods(beanClass,new ReflectionUtils.MethodCallback(){
    @Override @SuppressWarnings("unchecked") public void doWith(    Method method) throws IllegalArgumentException, IllegalAccessException {
      Class[] parameterTypes=method.getParameterTypes();
      Assert.isTrue(parameterTypes.length == 1,"method should have one and only one parameter.");
      SofaReference sofaReferenceAnnotation=method.getAnnotation(SofaReference.class);
      if (sofaReferenceAnnotation == null) {
        return;
      }
      AnnotationWrapperBuilder<SofaReference> builder=AnnotationWrapperBuilder.wrap(sofaReferenceAnnotation).withBinder(binder);
      sofaReferenceAnnotation=builder.build();
      Class<?> interfaceType=sofaReferenceAnnotation.interfaceType();
      if (interfaceType.equals(void.class)) {
        interfaceType=parameterTypes[0];
      }
      Object proxy=createReferenceProxy(sofaReferenceAnnotation,interfaceType);
      ReflectionUtils.invokeMethod(method,bean,proxy);
    }
  }
,new ReflectionUtils.MethodFilter(){
    @Override public boolean matches(    Method method){
      return method.isAnnotationPresent(SofaReference.class);
    }
  }
);
}
