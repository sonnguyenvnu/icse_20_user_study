private void _setContext(Context context,MethodVisitor mw){
  mw.visitVarInsn(ALOAD,1);
  mw.visitVarInsn(ALOAD,context.var("context"));
  mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"setContext","(" + desc(ParseContext.class) + ")V");
  Label endIf_=new Label();
  mw.visitVarInsn(ALOAD,context.var("childContext"));
  mw.visitJumpInsn(IFNULL,endIf_);
  mw.visitVarInsn(ALOAD,context.var("childContext"));
  mw.visitVarInsn(ALOAD,context.var("instance"));
  mw.visitFieldInsn(PUTFIELD,type(ParseContext.class),"object","Ljava/lang/Object;");
  mw.visitLabel(endIf_);
}
