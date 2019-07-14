private void _if_write_null(MethodVisitor mw,FieldInfo fieldInfo,Context context){
  Class<?> propertyClass=fieldInfo.fieldClass;
  Label _if=new Label();
  Label _else=new Label();
  Label _write_null=new Label();
  Label _end_if=new Label();
  mw.visitLabel(_if);
  JSONField annotation=fieldInfo.getAnnotation();
  int features=0;
  if (annotation != null) {
    features=SerializerFeature.of(annotation.serialzeFeatures());
  }
  JSONType jsonType=context.beanInfo.jsonType;
  if (jsonType != null) {
    features|=SerializerFeature.of(jsonType.serialzeFeatures());
  }
  int writeNullFeatures;
  if (propertyClass == String.class) {
    writeNullFeatures=SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullStringAsEmpty.getMask();
  }
 else   if (Number.class.isAssignableFrom(propertyClass)) {
    writeNullFeatures=SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullNumberAsZero.getMask();
  }
 else   if (Collection.class.isAssignableFrom(propertyClass)) {
    writeNullFeatures=SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullListAsEmpty.getMask();
  }
 else   if (Boolean.class == propertyClass) {
    writeNullFeatures=SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullBooleanAsFalse.getMask();
  }
 else {
    writeNullFeatures=SerializerFeature.WRITE_MAP_NULL_FEATURES;
  }
  if ((features & writeNullFeatures) == 0) {
    mw.visitVarInsn(ALOAD,context.var("out"));
    mw.visitLdcInsn(writeNullFeatures);
    mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"isEnabled","(I)Z");
    mw.visitJumpInsn(IFEQ,_else);
  }
  mw.visitLabel(_write_null);
  mw.visitVarInsn(ALOAD,context.var("out"));
  mw.visitVarInsn(ILOAD,context.var("seperator"));
  mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(I)V");
  _writeFieldName(mw,context);
  mw.visitVarInsn(ALOAD,context.var("out"));
  mw.visitLdcInsn(features);
  if (propertyClass == String.class || propertyClass == Character.class) {
    mw.visitLdcInsn(SerializerFeature.WriteNullStringAsEmpty.mask);
  }
 else   if (Number.class.isAssignableFrom(propertyClass)) {
    mw.visitLdcInsn(SerializerFeature.WriteNullNumberAsZero.mask);
  }
 else   if (propertyClass == Boolean.class) {
    mw.visitLdcInsn(SerializerFeature.WriteNullBooleanAsFalse.mask);
  }
 else   if (Collection.class.isAssignableFrom(propertyClass) || propertyClass.isArray()) {
    mw.visitLdcInsn(SerializerFeature.WriteNullListAsEmpty.mask);
  }
 else {
    mw.visitLdcInsn(0);
  }
  mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"writeNull","(II)V");
  _seperator(mw,context);
  mw.visitJumpInsn(GOTO,_end_if);
  mw.visitLabel(_else);
  mw.visitLabel(_end_if);
}
