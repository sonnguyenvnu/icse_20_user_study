private void _getCollectionFieldItemDeser(Context context,MethodVisitor mw,FieldInfo fieldInfo,Class<?> itemType){
  Label notNull_=new Label();
  mw.visitVarInsn(ALOAD,0);
  mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_list_item_deser__",desc(ObjectDeserializer.class));
  mw.visitJumpInsn(IFNONNULL,notNull_);
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,1);
  mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"getConfig","()" + desc(ParserConfig.class));
  mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(itemType)));
  mw.visitMethodInsn(INVOKEVIRTUAL,type(ParserConfig.class),"getDeserializer","(Ljava/lang/reflect/Type;)" + desc(ObjectDeserializer.class));
  mw.visitFieldInsn(PUTFIELD,context.className,fieldInfo.name + "_asm_list_item_deser__",desc(ObjectDeserializer.class));
  mw.visitLabel(notNull_);
  mw.visitVarInsn(ALOAD,0);
  mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_list_item_deser__",desc(ObjectDeserializer.class));
}
