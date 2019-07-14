private void generateSofaServiceDefinitionOnMethod(String beanId,AnnotatedBeanDefinition beanDefinition,ConfigurableListableBeanFactory beanFactory){
  Class<?> returnType;
  Class<?> declaringClass;
  List<Method> candidateMethods=new ArrayList<>();
  MethodMetadata methodMetadata=beanDefinition.getFactoryMethodMetadata();
  try {
    returnType=ClassUtils.forName(methodMetadata.getReturnTypeName(),null);
    declaringClass=ClassUtils.forName(methodMetadata.getDeclaringClassName(),null);
  }
 catch (  Throwable throwable) {
    SofaLogger.error(throwable,"Failed to parse factoryBeanMethod of BeanDefinition( {0} )",beanId);
    return;
  }
  if (methodMetadata instanceof StandardMethodMetadata) {
    candidateMethods.add(((StandardMethodMetadata)methodMetadata).getIntrospectedMethod());
  }
 else {
    for (    Method m : declaringClass.getDeclaredMethods()) {
      if (!m.getName().equals(methodMetadata.getMethodName()) || !m.getReturnType().getTypeName().equals(methodMetadata.getReturnTypeName())) {
        continue;
      }
      if (!AnnotatedElementUtils.hasAnnotation(m,Bean.class)) {
        continue;
      }
      Bean bean=m.getAnnotation(Bean.class);
      Set<String> beanNames=new HashSet<>();
      beanNames.add(m.getName());
      if (bean != null) {
        beanNames.addAll(Arrays.asList(bean.name()));
        beanNames.addAll(Arrays.asList(bean.value()));
      }
      if (!beanNames.contains(beanId)) {
        continue;
      }
      candidateMethods.add(m);
    }
  }
  if (candidateMethods.size() == 1) {
    SofaService sofaServiceAnnotation=candidateMethods.get(0).getAnnotation(SofaService.class);
    if (sofaServiceAnnotation == null) {
      sofaServiceAnnotation=returnType.getAnnotation(SofaService.class);
    }
    generateSofaServiceDefinition(beanId,sofaServiceAnnotation,returnType,beanDefinition,beanFactory);
    generateSofaReferenceDefinition(beanId,candidateMethods.get(0),beanFactory);
  }
 else   if (candidateMethods.size() > 1) {
    for (    Method m : candidateMethods) {
      if (AnnotatedElementUtils.hasAnnotation(m,SofaService.class) || AnnotatedElementUtils.hasAnnotation(returnType,SofaService.class)) {
        throw new FatalBeanException("multi @Bean-method with same name try to publish SofaService in " + declaringClass.getCanonicalName());
      }
      if (Stream.of(m.getParameterAnnotations()).flatMap(Stream::of).anyMatch(annotation -> annotation instanceof SofaReference)) {
        throw new FatalBeanException("multi @Bean-method with same name try to reference SofaService in" + declaringClass.getCanonicalName());
      }
    }
  }
}
