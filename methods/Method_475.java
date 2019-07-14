private void _init(ClassWriter cw,Context context){
  for (int i=0, size=context.fieldInfoList.length; i < size; ++i) {
    FieldInfo fieldInfo=context.fieldInfoList[i];
    FieldWriter fw=new FieldWriter(cw,ACC_PUBLIC,fieldInfo.name + "_asm_prefix__","[C");
    fw.visitEnd();
  }
  for (int i=0, size=context.fieldInfoList.length; i < size; ++i) {
    FieldInfo fieldInfo=context.fieldInfoList[i];
    Class<?> fieldClass=fieldInfo.fieldClass;
    if (fieldClass.isPrimitive()) {
      continue;
    }
    if (Collection.class.isAssignableFrom(fieldClass)) {
      FieldWriter fw=new FieldWriter(cw,ACC_PUBLIC,fieldInfo.name + "_asm_list_item_deser__",desc(ObjectDeserializer.class));
      fw.visitEnd();
    }
 else {
      FieldWriter fw=new FieldWriter(cw,ACC_PUBLIC,fieldInfo.name + "_asm_deser__",desc(ObjectDeserializer.class));
      fw.visitEnd();
    }
  }
  MethodVisitor mw=new MethodWriter(cw,ACC_PUBLIC,"<init>","(" + desc(ParserConfig.class) + desc(JavaBeanInfo.class) + ")V",null,null);
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,1);
  mw.visitVarInsn(ALOAD,2);
  mw.visitMethodInsn(INVOKESPECIAL,type(JavaBeanDeserializer.class),"<init>","(" + desc(ParserConfig.class) + desc(JavaBeanInfo.class) + ")V");
  for (int i=0, size=context.fieldInfoList.length; i < size; ++i) {
    FieldInfo fieldInfo=context.fieldInfoList[i];
    mw.visitVarInsn(ALOAD,0);
    mw.visitLdcInsn("\"" + fieldInfo.name + "\":");
    mw.visitMethodInsn(INVOKEVIRTUAL,"java/lang/String","toCharArray","()[C");
    mw.visitFieldInsn(PUTFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
  }
  mw.visitInsn(RETURN);
  mw.visitMaxs(4,4);
  mw.visitEnd();
}
