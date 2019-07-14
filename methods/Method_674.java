private final JavaBeanSerializer createASMSerializer(SerializeBeanInfo beanInfo) throws Exception {
  JavaBeanSerializer serializer=asmFactory.createJavaBeanSerializer(beanInfo);
  for (int i=0; i < serializer.sortedGetters.length; ++i) {
    FieldSerializer fieldDeser=serializer.sortedGetters[i];
    Class<?> fieldClass=fieldDeser.fieldInfo.fieldClass;
    if (fieldClass.isEnum()) {
      ObjectSerializer fieldSer=this.getObjectWriter(fieldClass);
      if (!(fieldSer instanceof EnumSerializer)) {
        serializer.writeDirect=false;
      }
    }
  }
  return serializer;
}
