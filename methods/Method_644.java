public Object getPropertyValue(Object object) throws InvocationTargetException, IllegalAccessException {
  Object propertyValue=fieldInfo.get(object);
  if (format != null && propertyValue != null) {
    if (fieldInfo.fieldClass == Date.class) {
      SimpleDateFormat dateFormat=new SimpleDateFormat(format,JSON.defaultLocale);
      dateFormat.setTimeZone(JSON.defaultTimeZone);
      return dateFormat.format(propertyValue);
    }
  }
  return propertyValue;
}
