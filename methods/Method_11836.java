private void validateDataPointFields(List<Throwable> errors){
  Field[] fields=getTestClass().getJavaClass().getDeclaredFields();
  for (  Field field : fields) {
    if (field.getAnnotation(DataPoint.class) == null && field.getAnnotation(DataPoints.class) == null) {
      continue;
    }
    if (!Modifier.isStatic(field.getModifiers())) {
      errors.add(new Error("DataPoint field " + field.getName() + " must be static"));
    }
    if (!Modifier.isPublic(field.getModifiers())) {
      errors.add(new Error("DataPoint field " + field.getName() + " must be public"));
    }
  }
}
