public void map(Object obj,LifecycleMethods methods) throws Exception {
  if (methods.hasResources()) {
    for (    Field field : methods.annotatedFields(Resources.class)) {
      Resources resources=field.getAnnotation(Resources.class);
      for (      Resource resource : resources.value()) {
        setFieldResource(obj,field,resource);
      }
    }
    for (    Field field : methods.annotatedFields(Resource.class)) {
      Resource resource=field.getAnnotation(Resource.class);
      setFieldResource(obj,field,resource);
    }
    for (    Method method : methods.annotatedMethods(Resources.class)) {
      Resources resources=method.getAnnotation(Resources.class);
      for (      Resource resource : resources.value()) {
        setMethodResource(obj,method,resource);
      }
    }
    for (    Method method : methods.annotatedMethods(Resource.class)) {
      Resource resource=method.getAnnotation(Resource.class);
      setMethodResource(obj,method,resource);
    }
    for (    Resources resources : methods.classAnnotations(Resources.class)) {
      for (      Resource resource : resources.value()) {
        loadClassResource(resource);
      }
    }
    for (    Resource resource : methods.classAnnotations(Resource.class)) {
      loadClassResource(resource);
    }
  }
}
