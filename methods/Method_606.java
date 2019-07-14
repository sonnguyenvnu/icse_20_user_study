public FieldDeserializer createFieldDeserializer(ParserConfig mapping,JavaBeanInfo beanInfo,FieldInfo fieldInfo){
  Class<?> clazz=beanInfo.clazz;
  Class<?> fieldClass=fieldInfo.fieldClass;
  Class<?> deserializeUsing=null;
  JSONField annotation=fieldInfo.getAnnotation();
  if (annotation != null) {
    deserializeUsing=annotation.deserializeUsing();
    if (deserializeUsing == Void.class) {
      deserializeUsing=null;
    }
  }
  if (deserializeUsing == null && (fieldClass == List.class || fieldClass == ArrayList.class)) {
    return new ArrayListTypeFieldDeserializer(mapping,clazz,fieldInfo);
  }
  return new DefaultFieldDeserializer(mapping,clazz,fieldInfo);
}
