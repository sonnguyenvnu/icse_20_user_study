private void _deserObject(Context context,MethodVisitor mw,FieldInfo fieldInfo,Class<?> fieldClass,int i){
  _getFieldDeser(context,mw,fieldInfo);
  Label instanceOfElse_=new Label(), instanceOfEnd_=new Label();
  if ((fieldInfo.parserFeatures & Feature.SupportArrayToBean.mask) != 0) {
    mw.visitInsn(DUP);
    mw.visitTypeInsn(INSTANCEOF,type(JavaBeanDeserializer.class));
    mw.visitJumpInsn(IFEQ,instanceOfElse_);
    mw.visitTypeInsn(CHECKCAST,type(JavaBeanDeserializer.class));
    mw.visitVarInsn(ALOAD,1);
    if (fieldInfo.fieldType instanceof Class) {
      mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldInfo.fieldClass)));
    }
 else {
      mw.visitVarInsn(ALOAD,0);
      mw.visitLdcInsn(i);
      mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"getFieldType","(I)Ljava/lang/reflect/Type;");
    }
    mw.visitLdcInsn(fieldInfo.name);
    mw.visitLdcInsn(fieldInfo.parserFeatures);
    mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"deserialze","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;");
    mw.visitTypeInsn(CHECKCAST,type(fieldClass));
    mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    mw.visitJumpInsn(GOTO,instanceOfEnd_);
    mw.visitLabel(instanceOfElse_);
  }
  mw.visitVarInsn(ALOAD,1);
  if (fieldInfo.fieldType instanceof Class) {
    mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldInfo.fieldClass)));
  }
 else {
    mw.visitVarInsn(ALOAD,0);
    mw.visitLdcInsn(i);
    mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"getFieldType","(I)Ljava/lang/reflect/Type;");
  }
  mw.visitLdcInsn(fieldInfo.name);
  mw.visitMethodInsn(INVOKEINTERFACE,type(ObjectDeserializer.class),"deserialze","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
  mw.visitTypeInsn(CHECKCAST,type(fieldClass));
  mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
  mw.visitLabel(instanceOfEnd_);
}
