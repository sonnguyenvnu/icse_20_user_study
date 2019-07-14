public static Annotation getXmlAccessorType(Class clazz){
  if (class_XmlAccessorType == null && !classXmlAccessorType_error) {
    try {
      class_XmlAccessorType=Class.forName("javax.xml.bind.annotation.XmlAccessorType");
    }
 catch (    Throwable ex) {
      classXmlAccessorType_error=true;
    }
  }
  if (class_XmlAccessorType == null) {
    return null;
  }
  return clazz.getAnnotation(class_XmlAccessorType);
}
