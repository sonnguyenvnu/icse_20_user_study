private void _deserialize_endCheck(Context context,MethodVisitor mw,Label reset_){
  mw.visitIntInsn(ILOAD,context.var("matchedCount"));
  mw.visitJumpInsn(IFLE,reset_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.RBRACE);
  mw.visitJumpInsn(IF_ICMPNE,reset_);
  _quickNextTokenComma(context,mw);
}
