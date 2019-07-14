private void _deserialze(ClassWriter cw,Context context){
  if (context.fieldInfoList.length == 0) {
    return;
  }
  for (  FieldInfo fieldInfo : context.fieldInfoList) {
    Class<?> fieldClass=fieldInfo.fieldClass;
    Type fieldType=fieldInfo.fieldType;
    if (fieldClass == char.class) {
      return;
    }
    if (Collection.class.isAssignableFrom(fieldClass)) {
      if (fieldType instanceof ParameterizedType) {
        Type itemType=((ParameterizedType)fieldType).getActualTypeArguments()[0];
        if (itemType instanceof Class) {
          continue;
        }
 else {
          return;
        }
      }
 else {
        return;
      }
    }
  }
  JavaBeanInfo beanInfo=context.beanInfo;
  context.fieldInfoList=beanInfo.sortedFields;
  MethodVisitor mw=new MethodWriter(cw,ACC_PUBLIC,"deserialze","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;",null,null);
  Label reset_=new Label();
  Label super_=new Label();
  Label return_=new Label();
  Label end_=new Label();
  defineVarLexer(context,mw);
{
    Label next_=new Label();
    mw.visitVarInsn(ALOAD,context.var("lexer"));
    mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"token","()I");
    mw.visitLdcInsn(JSONToken.LBRACKET);
    mw.visitJumpInsn(IF_ICMPNE,next_);
    if ((beanInfo.parserFeatures & Feature.SupportArrayToBean.mask) == 0) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ILOAD,4);
      mw.visitLdcInsn(Feature.SupportArrayToBean.mask);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"isEnabled","(II)Z");
      mw.visitJumpInsn(IFEQ,next_);
    }
    mw.visitVarInsn(ALOAD,0);
    mw.visitVarInsn(ALOAD,Context.parser);
    mw.visitVarInsn(ALOAD,2);
    mw.visitVarInsn(ALOAD,3);
    mw.visitInsn(ACONST_NULL);
    mw.visitMethodInsn(INVOKESPECIAL,context.className,"deserialzeArrayMapping","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
    mw.visitInsn(ARETURN);
    mw.visitLabel(next_);
  }
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(Feature.SortFeidFastMatch.mask);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"isEnabled","(I)Z");
  mw.visitJumpInsn(IFEQ,super_);
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(context.clazz.getName());
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanType","(Ljava/lang/String;)I");
  mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.NOT_MATCH);
  mw.visitJumpInsn(IF_ICMPEQ,super_);
  mw.visitVarInsn(ALOAD,1);
  mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"getContext","()" + desc(ParseContext.class));
  mw.visitVarInsn(ASTORE,context.var("mark_context"));
  mw.visitInsn(ICONST_0);
  mw.visitVarInsn(ISTORE,context.var("matchedCount"));
  _createInstance(context,mw);
{
    mw.visitVarInsn(ALOAD,1);
    mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"getContext","()" + desc(ParseContext.class));
    mw.visitVarInsn(ASTORE,context.var("context"));
    mw.visitVarInsn(ALOAD,1);
    mw.visitVarInsn(ALOAD,context.var("context"));
    mw.visitVarInsn(ALOAD,context.var("instance"));
    mw.visitVarInsn(ALOAD,3);
    mw.visitMethodInsn(INVOKEVIRTUAL,DefaultJSONParser,"setContext","(" + desc(ParseContext.class) + "Ljava/lang/Object;Ljava/lang/Object;)" + desc(ParseContext.class));
    mw.visitVarInsn(ASTORE,context.var("childContext"));
  }
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
  mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.END);
  mw.visitJumpInsn(IF_ICMPEQ,return_);
  mw.visitInsn(ICONST_0);
  mw.visitIntInsn(ISTORE,context.var("matchStat"));
  int fieldListSize=context.fieldInfoList.length;
  for (int i=0; i < fieldListSize; i+=32) {
    mw.visitInsn(ICONST_0);
    mw.visitVarInsn(ISTORE,context.var("_asm_flag_" + (i / 32)));
  }
  mw.visitVarInsn(ALOAD,context.var("lexer"));
  mw.visitLdcInsn(Feature.InitStringFieldAsEmpty.mask);
  mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"isEnabled","(I)Z");
  mw.visitIntInsn(ISTORE,context.var("initStringFieldAsEmpty"));
  for (int i=0; i < fieldListSize; ++i) {
    FieldInfo fieldInfo=context.fieldInfoList[i];
    Class<?> fieldClass=fieldInfo.fieldClass;
    if (fieldClass == boolean.class || fieldClass == byte.class || fieldClass == short.class || fieldClass == int.class) {
      mw.visitInsn(ICONST_0);
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == long.class) {
      mw.visitInsn(LCONST_0);
      mw.visitVarInsn(LSTORE,context.var(fieldInfo.name + "_asm",2));
    }
 else     if (fieldClass == float.class) {
      mw.visitInsn(FCONST_0);
      mw.visitVarInsn(FSTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == double.class) {
      mw.visitInsn(DCONST_0);
      mw.visitVarInsn(DSTORE,context.var(fieldInfo.name + "_asm",2));
    }
 else {
      if (fieldClass == String.class) {
        Label flagEnd_=new Label();
        Label flagElse_=new Label();
        mw.visitVarInsn(ILOAD,context.var("initStringFieldAsEmpty"));
        mw.visitJumpInsn(IFEQ,flagElse_);
        _setFlag(mw,context,i);
        mw.visitVarInsn(ALOAD,context.var("lexer"));
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"stringDefaultValue","()Ljava/lang/String;");
        mw.visitJumpInsn(GOTO,flagEnd_);
        mw.visitLabel(flagElse_);
        mw.visitInsn(ACONST_NULL);
        mw.visitLabel(flagEnd_);
      }
 else {
        mw.visitInsn(ACONST_NULL);
      }
      mw.visitTypeInsn(CHECKCAST,type(fieldClass));
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
  }
  for (int i=0; i < fieldListSize; ++i) {
    FieldInfo fieldInfo=context.fieldInfoList[i];
    Class<?> fieldClass=fieldInfo.fieldClass;
    Type fieldType=fieldInfo.fieldType;
    Label notMatch_=new Label();
    if (fieldClass == boolean.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldBoolean","([C)Z");
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == byte.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldInt","([C)I");
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == Byte.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldInt","([C)I");
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
 else     if (fieldClass == short.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldInt","([C)I");
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == Short.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldInt","([C)I");
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
 else     if (fieldClass == int.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldInt","([C)I");
      mw.visitVarInsn(ISTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == Integer.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldInt","([C)I");
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
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldLong","([C)J");
      mw.visitVarInsn(LSTORE,context.var(fieldInfo.name + "_asm",2));
    }
 else     if (fieldClass == Long.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldLong","([C)J");
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
 else     if (fieldClass == float.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldFloat","([C)F");
      mw.visitVarInsn(FSTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == Float.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldFloat","([C)F");
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
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldDouble","([C)D");
      mw.visitVarInsn(DSTORE,context.var(fieldInfo.name + "_asm",2));
    }
 else     if (fieldClass == Double.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldDouble","([C)D");
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
 else     if (fieldClass == String.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldString","([C)Ljava/lang/String;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == java.util.Date.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldDate","([C)Ljava/util/Date;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == java.util.UUID.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldUUID","([C)Ljava/util/UUID;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == BigDecimal.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldDecimal","([C)Ljava/math/BigDecimal;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == BigInteger.class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldBigInteger","([C)Ljava/math/BigInteger;");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == int[].class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldIntArray","([C)[I");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == float[].class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldFloatArray","([C)[F");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass == float[][].class) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldFloatArray2","([C)[[F");
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (fieldClass.isEnum()) {
      mw.visitVarInsn(ALOAD,0);
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      _getFieldDeser(context,mw,fieldInfo);
      mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"scanEnum","(L" + JSONLexerBase + ";[C" + desc(ObjectDeserializer.class) + ")Ljava/lang/Enum;");
      mw.visitTypeInsn(CHECKCAST,type(fieldClass));
      mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
    }
 else     if (Collection.class.isAssignableFrom(fieldClass)) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitVarInsn(ALOAD,0);
      mw.visitFieldInsn(GETFIELD,context.className,fieldInfo.name + "_asm_prefix__","[C");
      Class<?> itemClass=TypeUtils.getCollectionItemClass(fieldType);
      if (itemClass == String.class) {
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldClass)));
        mw.visitMethodInsn(INVOKEVIRTUAL,JSONLexerBase,"scanFieldStringArray","([CLjava/lang/Class;)" + desc(Collection.class));
        mw.visitVarInsn(ASTORE,context.var(fieldInfo.name + "_asm"));
      }
 else {
        _deserialze_list_obj(context,mw,reset_,fieldInfo,fieldClass,itemClass,i);
        if (i == fieldListSize - 1) {
          _deserialize_endCheck(context,mw,reset_);
        }
        continue;
      }
    }
 else {
      _deserialze_obj(context,mw,reset_,fieldInfo,fieldClass,i);
      if (i == fieldListSize - 1) {
        _deserialize_endCheck(context,mw,reset_);
      }
      continue;
    }
    mw.visitVarInsn(ALOAD,context.var("lexer"));
    mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
    Label flag_=new Label();
    mw.visitJumpInsn(IFLE,flag_);
    _setFlag(mw,context,i);
    mw.visitLabel(flag_);
    mw.visitVarInsn(ALOAD,context.var("lexer"));
    mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
    mw.visitInsn(DUP);
    mw.visitVarInsn(ISTORE,context.var("matchStat"));
    mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.NOT_MATCH);
    mw.visitJumpInsn(IF_ICMPEQ,reset_);
    mw.visitVarInsn(ALOAD,context.var("lexer"));
    mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
    mw.visitJumpInsn(IFLE,notMatch_);
    mw.visitVarInsn(ILOAD,context.var("matchedCount"));
    mw.visitInsn(ICONST_1);
    mw.visitInsn(IADD);
    mw.visitVarInsn(ISTORE,context.var("matchedCount"));
    mw.visitVarInsn(ALOAD,context.var("lexer"));
    mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
    mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.END);
    mw.visitJumpInsn(IF_ICMPEQ,end_);
    mw.visitLabel(notMatch_);
    if (i == fieldListSize - 1) {
      mw.visitVarInsn(ALOAD,context.var("lexer"));
      mw.visitFieldInsn(GETFIELD,JSONLexerBase,"matchStat","I");
      mw.visitLdcInsn(com.alibaba.fastjson.parser.JSONLexerBase.END);
      mw.visitJumpInsn(IF_ICMPNE,reset_);
    }
  }
  mw.visitLabel(end_);
  if (!context.clazz.isInterface() && !Modifier.isAbstract(context.clazz.getModifiers())) {
    _batchSet(context,mw);
  }
  mw.visitLabel(return_);
  _setContext(context,mw);
  mw.visitVarInsn(ALOAD,context.var("instance"));
  Method buildMethod=context.beanInfo.buildMethod;
  if (buildMethod != null) {
    mw.visitMethodInsn(INVOKEVIRTUAL,type(context.getInstClass()),buildMethod.getName(),"()" + desc(buildMethod.getReturnType()));
  }
  mw.visitInsn(ARETURN);
  mw.visitLabel(reset_);
  _batchSet(context,mw);
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,1);
  mw.visitVarInsn(ALOAD,2);
  mw.visitVarInsn(ALOAD,3);
  mw.visitVarInsn(ALOAD,context.var("instance"));
  mw.visitVarInsn(ILOAD,4);
  int flagSize=(fieldListSize / 32);
  if (fieldListSize != 0 && (fieldListSize % 32) != 0) {
    flagSize+=1;
  }
  if (flagSize == 1) {
    mw.visitInsn(ICONST_1);
  }
 else {
    mw.visitIntInsn(BIPUSH,flagSize);
  }
  mw.visitIntInsn(NEWARRAY,T_INT);
  for (int i=0; i < flagSize; ++i) {
    mw.visitInsn(DUP);
    if (i == 0) {
      mw.visitInsn(ICONST_0);
    }
 else     if (i == 1) {
      mw.visitInsn(ICONST_1);
    }
 else {
      mw.visitIntInsn(BIPUSH,i);
    }
    mw.visitVarInsn(ILOAD,context.var("_asm_flag_" + i));
    mw.visitInsn(IASTORE);
  }
  mw.visitMethodInsn(INVOKEVIRTUAL,type(JavaBeanDeserializer.class),"parseRest","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;I[I)Ljava/lang/Object;");
  mw.visitTypeInsn(CHECKCAST,type(context.clazz));
  mw.visitInsn(ARETURN);
  mw.visitLabel(super_);
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,1);
  mw.visitVarInsn(ALOAD,2);
  mw.visitVarInsn(ALOAD,3);
  mw.visitVarInsn(ILOAD,4);
  mw.visitMethodInsn(INVOKESPECIAL,type(JavaBeanDeserializer.class),"deserialze","(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;");
  mw.visitInsn(ARETURN);
  mw.visitMaxs(10,context.variantIndex);
  mw.visitEnd();
}
