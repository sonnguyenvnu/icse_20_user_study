private void validateDataPointMethods(List<Throwable> errors){
  Method[] methods=getTestClass().getJavaClass().getDeclaredMethods();
  for (  Method method : methods) {
    if (method.getAnnotation(DataPoint.class) == null && method.getAnnotation(DataPoints.class) == null) {
      continue;
    }
    if (!Modifier.isStatic(method.getModifiers())) {
      errors.add(new Error("DataPoint method " + method.getName() + " must be static"));
    }
    if (!Modifier.isPublic(method.getModifiers())) {
      errors.add(new Error("DataPoint method " + method.getName() + " must be public"));
    }
  }
}
