private void _list(Class<?> clazz,MethodVisitor mw,FieldInfo fieldInfo,Context context){
  Type propertyType=fieldInfo.fieldType;
  Type elementType=TypeUtils.getCollectionItemType(propertyType);
  Class<?> elementClass=null;
  if (elementType instanceof Class<?>) {
    elementClass=(Class<?>)elementType;
  }
  if (elementClass == Object.class || elementClass == Serializable.class) {
    elementClass=null;
  }
  Label end_=new Label(), else_=new Label(), endIf_=new Label();
  _nameApply(mw,fieldInfo,context,end_);
  _get(mw,context,fieldInfo);
  mw.visitTypeInsn(CHECKCAST,"java/util/List");
  mw.visitVarInsn(ASTORE,context.var("list"));
  _filters(mw,fieldInfo,context,end_);
  mw.visitVarInsn(ALOAD,context.var("list"));
  mw.visitJumpInsn(IFNONNULL,else_);
  _if_write_null(mw,fieldInfo,context);
  mw.visitJumpInsn(GOTO,endIf_);
  mw.visitLabel(else_);
  mw.visitVarInsn(ALOAD,context.var("out"));
  mw.visitVarInsn(ILOAD,context.var("seperator"));
  mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(I)V");
  _writeFieldName(mw,context);
  mw.visitVarInsn(ALOAD,context.var("list"));
  mw.visitMethodInsn(INVOKEINTERFACE,"java/util/List","size","()I");
  mw.visitVarInsn(ISTORE,context.var("size"));
  Label _else_3=new Label();
  Label _end_if_3=new Label();
  mw.visitVarInsn(ILOAD,context.var("size"));
  mw.visitInsn(ICONST_0);
  mw.visitJumpInsn(IF_ICMPNE,_else_3);
  mw.visitVarInsn(ALOAD,context.var("out"));
  mw.visitLdcInsn("[]");
  mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(Ljava/lang/String;)V");
  mw.visitJumpInsn(GOTO,_end_if_3);
  mw.visitLabel(_else_3);
  if (!context.nonContext) {
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitVarInsn(ALOAD,context.var("list"));
    mw.visitVarInsn(ALOAD,Context.fieldName);
    mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"setContext","(Ljava/lang/Object;Ljava/lang/Object;)V");
  }
  if (elementType == String.class && context.writeDirect) {
    mw.visitVarInsn(ALOAD,context.var("out"));
    mw.visitVarInsn(ALOAD,context.var("list"));
    mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(Ljava/util/List;)V");
  }
 else {
    mw.visitVarInsn(ALOAD,context.var("out"));
    mw.visitVarInsn(BIPUSH,'[');
    mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(I)V");
    Label for_=new Label(), forFirst_=new Label(), forEnd_=new Label();
    mw.visitInsn(ICONST_0);
    mw.visitVarInsn(ISTORE,context.var("i"));
    mw.visitLabel(for_);
    mw.visitVarInsn(ILOAD,context.var("i"));
    mw.visitVarInsn(ILOAD,context.var("size"));
    mw.visitJumpInsn(IF_ICMPGE,forEnd_);
    mw.visitVarInsn(ILOAD,context.var("i"));
    mw.visitJumpInsn(IFEQ,forFirst_);
    mw.visitVarInsn(ALOAD,context.var("out"));
    mw.visitVarInsn(BIPUSH,',');
    mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(I)V");
    mw.visitLabel(forFirst_);
    mw.visitVarInsn(ALOAD,context.var("list"));
    mw.visitVarInsn(ILOAD,context.var("i"));
    mw.visitMethodInsn(INVOKEINTERFACE,"java/util/List","get","(I)Ljava/lang/Object;");
    mw.visitVarInsn(ASTORE,context.var("list_item"));
    Label forItemNullEnd_=new Label(), forItemNullElse_=new Label();
    mw.visitVarInsn(ALOAD,context.var("list_item"));
    mw.visitJumpInsn(IFNONNULL,forItemNullElse_);
    mw.visitVarInsn(ALOAD,context.var("out"));
    mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"writeNull","()V");
    mw.visitJumpInsn(GOTO,forItemNullEnd_);
    mw.visitLabel(forItemNullElse_);
    Label forItemClassIfEnd_=new Label(), forItemClassIfElse_=new Label();
    if (elementClass != null && Modifier.isPublic(elementClass.getModifiers())) {
      mw.visitVarInsn(ALOAD,context.var("list_item"));
      mw.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object","getClass","()Ljava/lang/Class;");
      mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(elementClass)));
      mw.visitJumpInsn(IF_ACMPNE,forItemClassIfElse_);
      _getListFieldItemSer(context,mw,fieldInfo,elementClass);
      mw.visitVarInsn(ASTORE,context.var("list_item_desc"));
      Label instanceOfElse_=new Label(), instanceOfEnd_=new Label();
      if (context.writeDirect) {
        String writeMethodName=context.nonContext && context.writeDirect ? "writeDirectNonContext" : "write";
        mw.visitVarInsn(ALOAD,context.var("list_item_desc"));
        mw.visitTypeInsn(INSTANCEOF,JavaBeanSerializer);
        mw.visitJumpInsn(IFEQ,instanceOfElse_);
        mw.visitVarInsn(ALOAD,context.var("list_item_desc"));
        mw.visitTypeInsn(CHECKCAST,JavaBeanSerializer);
        mw.visitVarInsn(ALOAD,Context.serializer);
        mw.visitVarInsn(ALOAD,context.var("list_item"));
        if (context.nonContext) {
          mw.visitInsn(ACONST_NULL);
        }
 else {
          mw.visitVarInsn(ILOAD,context.var("i"));
          mw.visitMethodInsn(INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
        }
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(elementClass)));
        mw.visitLdcInsn(fieldInfo.serialzeFeatures);
        mw.visitMethodInsn(INVOKEVIRTUAL,JavaBeanSerializer,writeMethodName,"(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        mw.visitJumpInsn(GOTO,instanceOfEnd_);
        mw.visitLabel(instanceOfElse_);
      }
      mw.visitVarInsn(ALOAD,context.var("list_item_desc"));
      mw.visitVarInsn(ALOAD,Context.serializer);
      mw.visitVarInsn(ALOAD,context.var("list_item"));
      if (context.nonContext) {
        mw.visitInsn(ACONST_NULL);
      }
 else {
        mw.visitVarInsn(ILOAD,context.var("i"));
        mw.visitMethodInsn(INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
      }
      mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(elementClass)));
      mw.visitLdcInsn(fieldInfo.serialzeFeatures);
      mw.visitMethodInsn(INVOKEINTERFACE,ObjectSerializer,"write","(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
      mw.visitLabel(instanceOfEnd_);
      mw.visitJumpInsn(GOTO,forItemClassIfEnd_);
    }
    mw.visitLabel(forItemClassIfElse_);
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitVarInsn(ALOAD,context.var("list_item"));
    if (context.nonContext) {
      mw.visitInsn(ACONST_NULL);
    }
 else {
      mw.visitVarInsn(ILOAD,context.var("i"));
      mw.visitMethodInsn(INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
    }
    if (elementClass != null && Modifier.isPublic(elementClass.getModifiers())) {
      mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc((Class<?>)elementType)));
      mw.visitLdcInsn(fieldInfo.serialzeFeatures);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"writeWithFieldName","(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
    }
 else {
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"writeWithFieldName","(Ljava/lang/Object;Ljava/lang/Object;)V");
    }
    mw.visitLabel(forItemClassIfEnd_);
    mw.visitLabel(forItemNullEnd_);
    mw.visitIincInsn(context.var("i"),1);
    mw.visitJumpInsn(GOTO,for_);
    mw.visitLabel(forEnd_);
    mw.visitVarInsn(ALOAD,context.var("out"));
    mw.visitVarInsn(BIPUSH,']');
    mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"write","(I)V");
  }
{
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"popContext","()V");
  }
  mw.visitLabel(_end_if_3);
  _seperator(mw,context);
  mw.visitLabel(endIf_);
  mw.visitLabel(end_);
}
