private void _getFieldSer(Context context,MethodVisitor mw,FieldInfo fieldInfo){
  Label notNull_=new Label();
  mw.visitVarInsn(ALOAD,0);
  mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_ser_",ObjectSerializer_desc);
  mw.visitJumpInsn(IFNONNULL,notNull_);
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,Context.serializer);
  mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldInfo.fieldClass)));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"getObjectWriter","(Ljava/lang/Class;)" + ObjectSerializer_desc);
  mw.visitFieldInsn(PUTFIELD,context.className,fieldInfo.name + "_asm_ser_",ObjectSerializer_desc);
  mw.visitLabel(notNull_);
  mw.visitVarInsn(ALOAD,0);
  mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_ser_",ObjectSerializer_desc);
}
