private String validate(Object param,HttpMethod method){
  Field[] fields=param.getClass().getDeclaredFields();
  for (  Field field : fields) {
    if (field == null || !field.isAnnotationPresent(NotNullField.class)) {
      continue;
    }
    field.setAccessible(true);
    NotNullField notNullField=field.getAnnotation(NotNullField.class);
    try {
      if (Arrays.stream(notNullField.method()).anyMatch(m -> m.matches(method.name())) && (field.get(param) == null || StringUtils.isBlank(field.get(param).toString()))) {
        return notNullField.message();
      }
    }
 catch (    IllegalAccessException ignore) {
    }
    if (field.isAnnotationPresent(SizeField.class)) {
      SizeField size=field.getAnnotation(SizeField.class);
      try {
        if (ArrayUtils.contains(size.method(),method) && (field.get(param).toString().length() > size.max() || field.get(param).toString().length() < size.min())) {
          return notNullField.message();
        }
      }
 catch (      IllegalAccessException ignore) {
      }
    }
  }
  return CommonsConstant.BLANK;
}
