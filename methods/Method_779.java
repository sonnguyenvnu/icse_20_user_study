public static boolean isXmlField(Class clazz){
  if (class_XmlAccessorType == null && !classXmlAccessorType_error) {
    try {
      class_XmlAccessorType=Class.forName("javax.xml.bind.annotation.XmlAccessorType");
    }
 catch (    Throwable ex) {
      classXmlAccessorType_error=true;
    }
  }
  if (class_XmlAccessorType == null) {
    return false;
  }
  Annotation annotation=clazz.getAnnotation(class_XmlAccessorType);
  if (annotation == null) {
    return false;
  }
  if (method_XmlAccessorType_value == null && !classXmlAccessorType_error) {
    try {
      method_XmlAccessorType_value=class_XmlAccessorType.getMethod("value");
    }
 catch (    Throwable ex) {
      classXmlAccessorType_error=true;
    }
  }
  if (method_XmlAccessorType_value == null) {
    return false;
  }
  Object value=null;
  if (!classXmlAccessorType_error) {
    try {
      value=method_XmlAccessorType_value.invoke(annotation);
    }
 catch (    Throwable ex) {
      classXmlAccessorType_error=true;
    }
  }
  if (value == null) {
    return false;
  }
  if (class_XmlAccessType == null && !classXmlAccessorType_error) {
    try {
      class_XmlAccessType=Class.forName("javax.xml.bind.annotation.XmlAccessType");
      field_XmlAccessType_FIELD=class_XmlAccessType.getField("FIELD");
      field_XmlAccessType_FIELD_VALUE=field_XmlAccessType_FIELD.get(null);
    }
 catch (    Throwable ex) {
      classXmlAccessorType_error=true;
    }
  }
  return value == field_XmlAccessType_FIELD_VALUE;
}
