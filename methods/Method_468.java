private void _quickNextToken(Context context,MethodVisitor mw,int token){
  Label quickElse_=new Label(), quickEnd_=new Label();
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"getCurrent","()C");
  if (token == JSONToken.LBRACE) {
    mw.visitVarInsn(BIPUSH,'{');
  }
 else   if (token == JSONToken.LBRACKET) {
    mw.visitVarInsn(BIPUSH,'[');
  }
 else {
    throw new IllegalStateException();
  }
  mw.visitJumpInsn(IF_ICMPNE,quickElse_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"next","()C");
  mw.visitInsn(POP);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(token);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"setToken","(I)V");
  mw.visitJumpInsn(GOTO,quickEnd_);
  mw.visitLabel(quickElse_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(token);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
  mw.visitLabel(quickEnd_);
}
