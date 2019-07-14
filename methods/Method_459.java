private void _deserialzeArrayMapping(ClassWriter cw,Context context){
  MethodVisitor mw=new MethodWriter(cw,ACC_PUBLIC,"deserialzeArrayMapping","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",null,null);
  defineVarLexer(context,mw);
  _createInstance(context,mw);
  FieldInfo[] sortedFieldInfoList=context.beanInfo.sortedFields;
  int fieldListSize=sortedFieldInfoList.length;
  for (int i=0; i < fieldListSize; ++i) {
    final boolean last=(i == fieldListSize - 1);
    final char seperator=last ? ']' : ',';
    FieldInfo fieldInfo=sortedFieldInfoList[i];
    Class<?> fieldClass=fieldInfo.fieldClass;
    Type fieldType=fieldInfo.fieldType;
    if (fieldClass == byte.class || fieldClass == short.class || fieldClass == int.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanInt","(C)I");
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == Byte.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanInt","(C)I");
      mw.visitMethodInsn(INVOKESTATIC,"java/lang/Byte","valueOf","(B)Ljava/lang/Byte;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      Label valueNullEnd_=new Label();
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.VALUE_NULL);
      mw.visitJumpInsn(IF_ICMPNE,valueNullEnd_);
      mw.visitInsn(ACONST_NULL);
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      mw.visitLabel(valueNullEnd_);
    }
 else     if (fieldClass == Short.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanInt","(C)I");
      mw.visitMethodInsn(INVOKESTATIC,"java/lang/Short","valueOf","(S)Ljava/lang/Short;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      Label valueNullEnd_=new Label();
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.VALUE_NULL);
      mw.visitJumpInsn(IF_ICMPNE,valueNullEnd_);
      mw.visitInsn(ACONST_NULL);
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      mw.visitLabel(valueNullEnd_);
    }
 else     if (fieldClass == Integer.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanInt","(C)I");
      mw.visitMethodInsn(INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      Label valueNullEnd_=new Label();
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.VALUE_NULL);
      mw.visitJumpInsn(IF_ICMPNE,valueNullEnd_);
      mw.visitInsn(ACONST_NULL);
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      mw.visitLabel(valueNullEnd_);
    }
 else     if (fieldClass == long.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanLong","(C)J");
      mw.visitVarInsn(LSTORE,context.var(fieldInfo.name + "_asm",2));
    }
 else     if (fieldClass == Long.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanLong","(C)J");
      mw.visitMethodInsn(INVOKESTATIC,"java/lang/Long","valueOf","(J)Ljava/lang/Long;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      Label valueNullEnd_=new Label();
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.VALUE_NULL);
      mw.visitJumpInsn(IF_ICMPNE,valueNullEnd_);
      mw.visitInsn(ACONST_NULL);
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      mw.visitLabel(valueNullEnd_);
    }
 else     if (fieldClass == boolean.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanBoolean","(C)Z");
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == float.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFloat","(C)F");
      mw.visitVarInsn(FSTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == Float.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFloat","(C)F");
      mw.visitMethodInsn(INVOKESTATIC,"java/lang/Float","valueOf","(F)Ljava/lang/Float;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      Label valueNullEnd_=new Label();
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.VALUE_NULL);
      mw.visitJumpInsn(IF_ICMPNE,valueNullEnd_);
      mw.visitInsn(ACONST_NULL);
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      mw.visitLabel(valueNullEnd_);
    }
 else     if (fieldClass == double.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanDouble","(C)D");
      mw.visitVarInsn(DSTORE,context.var(fieldInfo.name + "_asm",2));
    }
 else     if (fieldClass == Double.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanDouble","(C)D");
      mw.visitMethodInsn(INVOKESTATIC,"java/lang/Double","valueOf","(D)Ljava/lang/Double;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      Label valueNullEnd_=new Label();
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.VALUE_NULL);
      mw.visitJumpInsn(IF_ICMPNE,valueNullEnd_);
      mw.visitInsn(ACONST_NULL);
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      mw.visitLabel(valueNullEnd_);
    }
 else     if (fieldClass == char.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanString","(C)Ljava/lang/String;");
      mw.visitInsn(ICONST_0);
      mw.visitMethodInsn(INVOKEVIRTUAL,"java/lang/String","charAt","(I)C");
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == String.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanString","(C)Ljava/lang/String;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == BigDecimal.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanDecimal","(C)Ljava/math/BigDecimal;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == java.util.Date.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanDate","(C)Ljava/util/Date;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == java.util.UUID.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanUUID","(C)Ljava/util/UUID;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass.isEnum()) {
      Label enumNumIf_=new Label();
      Label enumNumErr_=new Label();
      Label enumStore_=new Label();
      Label enumQuote_=new Label();
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"getCurrent","()C");
      mw.visitInsn(DUP);
      mw.visitVarInsn(ISTORE,context.var("ch"));
      mw.visitLdcInsn((int)'n');
      mw.visitJumpInsn(IF_ICMPEQ,enumQuote_);
      mw.visitVarInsn(ILOAD,context.var("ch"));
      mw.visitLdcInsn((int)'\"');
      mw.visitJumpInsn(IF_ICMPNE,enumNumIf_);
      mw.visitLabel(enumQuote_);
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldClass)));
      mw.visitVarInsn(ALOAD,1);
      mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"getSymbolTable","()" + desc(SymbolTable.class));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanEnum","(Ljava/lang/Class;" + desc(SymbolTable.class) + "C)Ljava/lang/Enum;");
      mw.visitJumpInsn(GOTO,enumStore_);
      mw.visitLabel(enumNumIf_);
      mw.visitVarInsn(ILOAD,context.var("ch"));
      mw.visitLdcInsn((int)'0');
      mw.visitJumpInsn(IF_ICMPLT,enumNumErr_);
      mw.visitVarInsn(ILOAD,context.var("ch"));
      mw.visitLdcInsn((int)'9');
      mw.visitJumpInsn(IF_ICMPGT,enumNumErr_);
      _getFieldDeser(context,mw,fieldInfo);
      mw.visitTypeInsn(CHECKCAST,type(EnumDeserializer.class));
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanInt","(C)I");
      mw.visitMethodInsn(INVOKEVIRTUAL,type(EnumDeserializer.class),"valueOf","(I)Ljava/lang/Enum;");
      mw.visitJumpInsn(GOTO,enumStore_);
      mw.visitLabel(enumNumErr_);
      mw.visitVarInsn(ALOAD,0);
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(BIPUSH,seperator);
      mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"scanEnum","(L" + JSONLexerBase + ";C)Ljava/lang/Enum;");
      mw.visitLabel(enumStore_);
      mw.visitTypeInsn(CHECKCAST,type(fieldClass));
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (Collection.class.isAssignableFrom(fieldClass)) {
      Class<?> itemClass=TypeUtils.getCollectionItemClass(fieldType);
      if (itemClass == String.class) {
        if (fieldClass == List.class || fieldClass == Collections.class || fieldClass == ArrayList.class) {
          mw.visitTypeInsn(NEW,type(ArrayList.class));
          mw.visitInsn(DUP);
          mw.visitMethodInsn(INVOKESPECIAL,type(ArrayList.class),"<init>","()V");
        }
 else {
          mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldClass)));
          mw.visitMethodInsn(INVOKESTATIC,type(TypeUtils.class),"createCollection","(Ljava/lang/Class;)Ljava/util/Collection;");
        }
        mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitVarInsn(ALOAD,context.var(fieldInfo.name + "_asm"));
        mw.visitVarInsn(BIPUSH,seperator);
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanStringArray","(Ljava/util/Collection;C)V");
        Label valueNullEnd_=new Label();
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
        mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.VALUE_NULL);
        mw.visitJumpInsn(IF_ICMPNE,valueNullEnd_);
        mw.visitInsn(ACONST_NULL);
        mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
        mw.visitLabel(valueNullEnd_);
      }
 else {
        Label notError_=new Label();
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
        mw.visitVarInsn(ISTORE,context.var("token"));
        mw.visitVarInsn(ILOAD,context.var("token"));
        int token=i == 0 ? JSONToken.LBRACKET : JSONToken.COMMA;
        mw.visitLdcInsn(token);
        mw.visitJumpInsn(IF_ICMPEQ,notError_);
        mw.visitVarInsn(ALOAD,1);
        mw.visitLdcInsn(token);
        mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"throwException","(I)V");
        mw.visitLabel(notError_);
        Label quickElse_=new Label(), quickEnd_=new Label();
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"getCurrent","()C");
        mw.visitVarInsn(BIPUSH,'[');
        mw.visitJumpInsn(IF_ICMPNE,quickElse_);
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"next","()C");
        mw.visitInsn(POP);
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitLdcInsn(JSONToken.LBRACKET);
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"setToken","(I)V");
        mw.visitJumpInsn(GOTO,quickEnd_);
        mw.visitLabel(quickElse_);
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitLdcInsn(JSONToken.LBRACKET);
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
        mw.visitLabel(quickEnd_);
        _newCollection(mw,fieldClass,i,false);
        mw.visitInsn(DUP);
        mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
        _getCollectionFieldItemDeser(context,mw,fieldInfo,itemClass);
        mw.visitVarInsn(ALOAD,1);
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(itemClass)));
        mw.visitVarInsn(ALOAD,3);
        mw.visitMethodInsn(INVOKESTATIC,type(JavaBeanDeserializer.class),"parseArray","(Ljava/util/Collection;" + desc(ObjectDeserializer.class) + "L" + DefaultJSONParser + ";" + "Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
      }
    }
 else     if (fieldClass.isArray()) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONToken.LBRACKET);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
      mw.visitVarInsn(ALOAD,Context.parser);
      mw.visitVarInsn(ALOAD,0);
      mw.visitLdcInsn(i);
      mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"getFieldType","(I)Ljava/lang/reflect/Type;");
      mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"parseObject","(Ljava/lang/reflect/Type;)Ljava/lang/Object;");
      mw.visitTypeInsn(CHECKCAST,type(fieldClass));
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else {
      Label objElseIf_=new Label();
      Label objEndIf_=new Label();
      if (fieldClass == java.util.Date.class) {
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"getCurrent","()C");
        mw.visitLdcInsn((int)'1');
        mw.visitJumpInsn(IF_ICMPNE,objElseIf_);
        mw.visitTypeInsn(NEW,type(java.util.Date.class));
        mw.visitInsn(DUP);
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitVarInsn(BIPUSH,seperator);
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanLong","(C)J");
        mw.visitMethodInsn(INVOKESPECIAL,type(java.util.Date.class),"<init>","(J)V");
        mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
        mw.visitJumpInsn(GOTO,objEndIf_);
      }
      mw.visitLabel(objElseIf_);
      _quickNextToken(context,mw,JSONToken.LBRACKET);
      _deserObject(context,mw,fieldInfo,fieldClass,i);
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
      mw.visitLdcInsn(JSONToken.RBRACKET);
      mw.visitJumpInsn(IF_ICMPEQ,objEndIf_);
      mw.visitVarInsn(ALOAD,0);
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      if (!last) {
        mw.visitLdcInsn(JSONToken.COMMA);
      }
 else {
        mw.visitLdcInsn(JSONToken.RBRACKET);
      }
      mw.visitMethodInsn(INVOKESPECIAL,type(JavaBeanDeserializer.class),"check","(" + desc(JSONLexer.class) + "I)V");
      mw.visitLabel(objEndIf_);
      continue;
    }
  }
  _batchSet(context,mw,false);
  Label quickElse_=new Label(), quickElseIf_=new Label(), quickElseIfEOI_=new Label(), quickEnd_=new Label();
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"getCurrent","()C");
  mw.visitInsn(DUP);
  mw.visitVarInsn(ISTORE,context.var("ch"));
  mw.visitVarInsn(BIPUSH,',');
  mw.visitJumpInsn(IF_ICMPNE,quickElseIf_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"next","()C");
  mw.visitInsn(POP);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(JSONToken.COMMA);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"setToken","(I)V");
  mw.visitJumpInsn(GOTO,quickEnd_);
  mw.visitLabel(quickElseIf_);
  mw.visitVarInsn(ILOAD,context.var("ch"));
  mw.visitVarInsn(BIPUSH,']');
  mw.visitJumpInsn(IF_ICMPNE,quickElseIfEOI_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"next","()C");
  mw.visitInsn(POP);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(JSONToken.RBRACKET);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"setToken","(I)V");
  mw.visitJumpInsn(GOTO,quickEnd_);
  mw.visitLabel(quickElseIfEOI_);
  mw.visitVarInsn(ILOAD,context.var("ch"));
  mw.visitVarInsn(BIPUSH,(char)JSONLexer.EOI);
  mw.visitJumpInsn(IF_ICMPNE,quickElse_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"next","()C");
  mw.visitInsn(POP);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(JSONToken.EOF);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"setToken","(I)V");
  mw.visitJumpInsn(GOTO,quickEnd_);
  mw.visitLabel(quickElse_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(JSONToken.COMMA);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"nextToken","(I)V");
  mw.visitLabel(quickEnd_);
  mw.visitVarInsn(ALOAD,context.var("instance"));
  mw.visitInsn(ARETURN);
  mw.visitMaxs(5,context.variantIndex);
  mw.visitEnd();
}
