private void _deserialze_list_obj(Context context,MethodVisitor mw,Label reset_,FieldInfo fieldInfo,Class<?> fieldClass,Class<?> itemType,int i){
  Label _end_if=new Label();
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"matchField","([C)Z");
  mw.visitJumpInsn(IFEQ,_end_if);
  _setFlag(mw,context,i);
  Label valueNotNull_=new Label();
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.NULL);
  mw.visitJumpInsn(IF_ICMPNE,valueNotNull_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(JSONToken.COMMA);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
  mw.visitJumpInsn(GOTO,_end_if);
  mw.visitLabel(valueNotNull_);
  Label storeCollection_=new Label(), endSet_=new Label(), lbacketNormal_=new Label();
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.SET);
  mw.visitJumpInsn(IF_ICMPNE,endSet_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(JSONToken.LBRACKET);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
  _newCollection(mw,fieldClass,i,true);
  mw.visitJumpInsn(GOTO,storeCollection_);
  mw.visitLabel(endSet_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.LBRACKET);
  mw.visitJumpInsn(IF_ICMPEQ,lbacketNormal_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.LBRACE);
  mw.visitJumpInsn(IF_ICMPNE,reset_);
  _newCollection(mw,fieldClass,i,false);
  mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
  _getCollectionFieldItemDeser(context,mw,fieldInfo,itemType);
  mw.visitVarInsn(ALOAD,1);
  mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(itemType)));
  mw.visitInsn(ICONST_0);
  mw.visitMethodInsn(INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
  mw.visitMethodInsn(INVOKEINTERFACE,type(ObjectDeserializer.class),"deserialze","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
  mw.visitVarInsn(ASTORE,context.var("list_item_value"));
  mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
  mw.visitVarInsn(ALOAD,context.var("list_item_value"));
  if (fieldClass.isInterface()) {
    mw.visitMethodInsn(INVOKEINTERFACE,type(fieldClass),"add","(Ljava/lang/Object;)Z");
  }
 else {
    mw.visitMethodInsn(INVOKEVIRTUAL,type(fieldClass),"add","(Ljava/lang/Object;)Z");
  }
  mw.visitInsn(POP);
  mw.visitJumpInsn(GOTO,_end_if);
  mw.visitLabel(lbacketNormal_);
  _newCollection(mw,fieldClass,i,false);
  mw.visitLabel(storeCollection_);
  mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
  boolean isPrimitive=ParserConfig.isPrimitive2(fieldInfo.fieldClass);
  _getCollectionFieldItemDeser(context,mw,fieldInfo,itemType);
  if (isPrimitive) {
    mw.visitMethodInsn(INVOKEINTERFACE,type(ObjectDeserializer.class),"getFastMatchToken","()I");
    mw.visitVarInsn(ISTORE,context.var("fastMatchToken"));
    mw.visitVarInsn(ALOAD,context.var("lexer"));
    mw.visitVarInsn(ILOAD,context.var("fastMatchToken"));
    mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
  }
 else {
    mw.visitInsn(POP);
    mw.visitLdcInsn(JSONToken.LBRACE);
    mw.visitVarInsn(ISTORE,context.var("fastMatchToken"));
    _quickNextToken(context,mw,JSONToken.LBRACE);
  }
{
    mw.visitVarInsn(ALOAD,1);
    mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"getContext","()" + desc(ParseContext.class));
    mw.visitVarInsn(ASTORE,context.var("listContext"));
    mw.visitVarInsn(ALOAD,1);
    mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
    mw.visitLdcInsn(fieldInfo.name);
    mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"setContext","(Ljava/lang/Object;Ljava/lang/Object;)" + desc(ParseContext.class));
    mw.visitInsn(POP);
  }
  Label loop_=new Label();
  Label loop_end_=new Label();
  mw.visitInsn(ICONST_0);
  mw.visitVarInsn(ISTORE,context.var("i"));
  mw.visitLabel(loop_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.RBRACKET);
  mw.visitJumpInsn(IF_ICMPEQ,loop_end_);
  mw.visitVarInsn(ALOAD,0);
  mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_list_item_deser__",desc(ObjectDeserializer.class));
  mw.visitVarInsn(ALOAD,1);
  mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(itemType)));
  mw.visitVarInsn(ILOAD,context.var("i"));
  mw.visitMethodInsn(INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
  mw.visitMethodInsn(INVOKEINTERFACE,type(ObjectDeserializer.class),"deserialze","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
  mw.visitVarInsn(ASTORE,context.var("list_item_value"));
  mw.visitIincInsn(context.var("i"),1);
  mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
  mw.visitVarInsn(ALOAD,context.var("list_item_value"));
  if (fieldClass.isInterface()) {
    mw.visitMethodInsn(INVOKEINTERFACE,type(fieldClass),"add","(Ljava/lang/Object;)Z");
  }
 else {
    mw.visitMethodInsn(INVOKEVIRTUAL,type(fieldClass),"add","(Ljava/lang/Object;)Z");
  }
  mw.visitInsn(POP);
  mw.visitVarInsn(ALOAD,1);
  mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
  mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"checkListResolve","(Ljava/util/Collection;)V");
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.COMMA);
  mw.visitJumpInsn(IF_ICMPNE,loop_);
  if (isPrimitive) {
    mw.visitVarInsn(ALOAD,context.var("lexer"));
    mw.visitVarInsn(ILOAD,context.var("fastMatchToken"));
    mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
  }
 else {
    _quickNextToken(context,mw,JSONToken.LBRACE);
  }
  mw.visitJumpInsn(GOTO,loop_);
  mw.visitLabel(loop_end_);
{
    mw.visitVarInsn(ALOAD,1);
    mw.visitVarInsn(ALOAD,context.var("listContext"));
    mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"setContext","(" + desc(ParseContext.class) + ")V");
  }
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
  mw.visitLdcInsn(JSONToken.RBRACKET);
  mw.visitJumpInsn(IF_ICMPNE,reset_);
  _quickNextTokenComma(context,mw);
  mw.visitLabel(_end_if);
}
