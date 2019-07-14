private void _writeObject(MethodVisitor mw,FieldInfo fieldInfo,Context context,Label _end){
  String format=fieldInfo.getFormat();
  Class<?> fieldClass=fieldInfo.fieldClass;
  Label notNull_=new Label();
  if (context.writeDirect) {
    mw.visitVarInsn(ALOAD,context.var("object"));
  }
 else {
    mw.visitVarInsn(ALOAD,Context.processValue);
  }
  mw.visitInsn(DUP);
  mw.visitVarInsn(ASTORE,context.var("object"));
  mw.visitJumpInsn(IFNONNULL,notNull_);
  _if_write_null(mw,fieldInfo,context);
  mw.visitJumpInsn(GOTO,_end);
  mw.visitLabel(notNull_);
  mw.visitVarInsn(ALOAD,context.var("out"));
  mw.visitVarInsn(ILOAD,context.var("seperator"));
  mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(I)V");
  _writeFieldName(mw,context);
  Label classIfEnd_=new Label(), classIfElse_=new Label();
  if (Modifier.isPublic(fieldClass.getModifiers()) && !ParserConfig.isPrimitive2(fieldClass)) {
    mw.visitVarInsn(ALOAD,context.var("object"));
    mw.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object","getClass","()Ljava/lang/Class;");
    mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldClass)));
    mw.visitJumpInsn(IF_ACMPNE,classIfElse_);
    _getFieldSer(context,mw,fieldInfo);
    mw.visitVarInsn(ASTORE,context.var("fied_ser"));
    Label instanceOfElse_=new Label(), instanceOfEnd_=new Label();
    mw.visitVarInsn(ALOAD,context.var("fied_ser"));
    mw.visitTypeInsn(INSTANCEOF,JavaBeanSerializer);
    mw.visitJumpInsn(IFEQ,instanceOfElse_);
    boolean disableCircularReferenceDetect=(fieldInfo.serialzeFeatures & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
    boolean fieldBeanToArray=(fieldInfo.serialzeFeatures & SerializerFeature.BeanToArray.mask) != 0;
    String writeMethodName;
    if (disableCircularReferenceDetect || (context.nonContext && context.writeDirect)) {
      writeMethodName=fieldBeanToArray ? "writeAsArrayNonContext" : "writeDirectNonContext";
    }
 else {
      writeMethodName=fieldBeanToArray ? "writeAsArray" : "write";
    }
    mw.visitVarInsn(ALOAD,context.var("fied_ser"));
    mw.visitTypeInsn(CHECKCAST,JavaBeanSerializer);
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitVarInsn(ALOAD,context.var("object"));
    mw.visitVarInsn(ALOAD,Context.fieldName);
    mw.visitVarInsn(ALOAD,0);
    mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_fieldType","Ljava/lang/reflect/Type;");
    mw.visitLdcInsn(fieldInfo.serialzeFeatures);
    mw.visitMethodInsn(INVOKEVIRTUAL,JavaBeanSerializer,writeMethodName,"(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
    mw.visitJumpInsn(GOTO,instanceOfEnd_);
    mw.visitLabel(instanceOfElse_);
    mw.visitVarInsn(ALOAD,context.var("fied_ser"));
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitVarInsn(ALOAD,context.var("object"));
    mw.visitVarInsn(ALOAD,Context.fieldName);
    mw.visitVarInsn(ALOAD,0);
    mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_fieldType","Ljava/lang/reflect/Type;");
    mw.visitLdcInsn(fieldInfo.serialzeFeatures);
    mw.visitMethodInsn(INVOKEINTERFACE,ObjectSerializer,"write","(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
    mw.visitLabel(instanceOfEnd_);
    mw.visitJumpInsn(GOTO,classIfEnd_);
  }
  mw.visitLabel(classIfElse_);
  mw.visitVarInsn(ALOAD,Context.serializer);
  if (context.writeDirect) {
    mw.visitVarInsn(ALOAD,context.var("object"));
  }
 else {
    mw.visitVarInsn(ALOAD,Context.processValue);
  }
  if (format != null) {
    mw.visitLdcInsn(format);
    mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"writeWithFormat","(Ljava/lang/Object;Ljava/lang/String;)V");
  }
 else {
    mw.visitVarInsn(ALOAD,Context.fieldName);
    if (fieldInfo.fieldType instanceof Class<?> && ((Class<?>)fieldInfo.fieldType).isPrimitive()) {
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"writeWithFieldName","(Ljava/lang/Object;Ljava/lang/Object;)V");
    }
 else {
      if (fieldInfo.fieldClass == String.class) {
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(String.class)));
      }
 else {
        mw.visitVarInsn(ALOAD,0);
        mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_fieldType","Ljava/lang/reflect/Type;");
      }
      mw.visitLdcInsn(fieldInfo.serialzeFeatures);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"writeWithFieldName","(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
    }
  }
  mw.visitLabel(classIfEnd_);
  _seperator(mw,context);
}
