public JavaBeanSerializer createJavaBeanSerializer(SerializeBeanInfo beanInfo) throws Exception {
  Class<?> clazz=beanInfo.beanType;
  if (clazz.isPrimitive()) {
    throw new JSONException("unsupportd class " + clazz.getName());
  }
  JSONType jsonType=TypeUtils.getAnnotation(clazz,JSONType.class);
  FieldInfo[] unsortedGetters=beanInfo.fields;
  for (  FieldInfo fieldInfo : unsortedGetters) {
    if (fieldInfo.field == null && fieldInfo.method != null && fieldInfo.method.getDeclaringClass().isInterface()) {
      return new JavaBeanSerializer(beanInfo);
    }
  }
  FieldInfo[] getters=beanInfo.sortedFields;
  boolean nativeSorted=beanInfo.sortedFields == beanInfo.fields;
  if (getters.length > 256) {
    return new JavaBeanSerializer(beanInfo);
  }
  for (  FieldInfo getter : getters) {
    if (!ASMUtils.checkName(getter.getMember().getName())) {
      return new JavaBeanSerializer(beanInfo);
    }
  }
  String className="ASMSerializer_" + seed.incrementAndGet() + "_" + clazz.getSimpleName();
  String classNameType;
  String classNameFull;
  Package pkg=ASMSerializerFactory.class.getPackage();
  if (pkg != null) {
    String packageName=pkg.getName();
    classNameType=packageName.replace('.','/') + "/" + className;
    classNameFull=packageName + "." + className;
  }
 else {
    classNameType=className;
    classNameFull=className;
  }
  String packageName=ASMSerializerFactory.class.getPackage().getName();
  ClassWriter cw=new ClassWriter();
  cw.visit(V1_5,ACC_PUBLIC + ACC_SUPER,classNameType,JavaBeanSerializer,new String[]{ObjectSerializer});
  for (  FieldInfo fieldInfo : getters) {
    if (fieldInfo.fieldClass.isPrimitive() || fieldInfo.fieldClass == String.class) {
      continue;
    }
    new FieldWriter(cw,ACC_PUBLIC,fieldInfo.name + "_asm_fieldType","Ljava/lang/reflect/Type;").visitEnd();
    if (List.class.isAssignableFrom(fieldInfo.fieldClass)) {
      new FieldWriter(cw,ACC_PUBLIC,fieldInfo.name + "_asm_list_item_ser_",ObjectSerializer_desc).visitEnd();
    }
    new FieldWriter(cw,ACC_PUBLIC,fieldInfo.name + "_asm_ser_",ObjectSerializer_desc).visitEnd();
  }
  MethodVisitor mw=new MethodWriter(cw,ACC_PUBLIC,"<init>","(" + desc(SerializeBeanInfo.class) + ")V",null,null);
  mw.visitVarInsn(ALOAD,0);
  mw.visitVarInsn(ALOAD,1);
  mw.visitMethodInsn(INVOKESPECIAL,JavaBeanSerializer,"<init>","(" + desc(SerializeBeanInfo.class) + ")V");
  for (int i=0; i < getters.length; ++i) {
    FieldInfo fieldInfo=getters[i];
    if (fieldInfo.fieldClass.isPrimitive() || fieldInfo.fieldClass == String.class) {
      continue;
    }
    mw.visitVarInsn(ALOAD,0);
    if (fieldInfo.method != null) {
      mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(desc(fieldInfo.declaringClass)));
      mw.visitLdcInsn(fieldInfo.method.getName());
      mw.visitMethodInsn(INVOKESTATIC,type(ASMUtils.class),"getMethodType","(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
    }
 else {
      mw.visitVarInsn(ALOAD,0);
      mw.visitLdcInsn(i);
      mw.visitMethodInsn(INVOKESPECIAL,JavaBeanSerializer,"getFieldType","(I)Ljava/lang/reflect/Type;");
    }
    mw.visitFieldInsn(PUTFIELD,classNameType,fieldInfo.name + "_asm_fieldType","Ljava/lang/reflect/Type;");
  }
  mw.visitInsn(RETURN);
  mw.visitMaxs(4,4);
  mw.visitEnd();
  boolean DisableCircularReferenceDetect=false;
  if (jsonType != null) {
    for (    SerializerFeature featrues : jsonType.serialzeFeatures()) {
      if (featrues == SerializerFeature.DisableCircularReferenceDetect) {
        DisableCircularReferenceDetect=true;
        break;
      }
    }
  }
  for (int i=0; i < 3; ++i) {
    String methodName;
    boolean nonContext=DisableCircularReferenceDetect;
    boolean writeDirect=false;
    if (i == 0) {
      methodName="write";
      writeDirect=true;
    }
 else     if (i == 1) {
      methodName="writeNormal";
    }
 else {
      writeDirect=true;
      nonContext=true;
      methodName="writeDirectNonContext";
    }
    Context context=new Context(getters,beanInfo,classNameType,writeDirect,nonContext);
    mw=new MethodWriter(cw,ACC_PUBLIC,methodName,"(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V",null,new String[]{"java/io/IOException"});
{
      Label endIf_=new Label();
      mw.visitVarInsn(ALOAD,Context.obj);
      mw.visitJumpInsn(IFNONNULL,endIf_);
      mw.visitVarInsn(ALOAD,Context.serializer);
      mw.visitMethodInsn(INVOKEVIRTUAL,JSONSerializer,"writeNull","()V");
      mw.visitInsn(RETURN);
      mw.visitLabel(endIf_);
    }
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitFieldInsn(GETFIELD,JSONSerializer,"out",SerializeWriter_desc);
    mw.visitVarInsn(ASTORE,context.var("out"));
    if ((!nativeSorted) && !context.writeDirect) {
      if (jsonType == null || jsonType.alphabetic()) {
        Label _else=new Label();
        mw.visitVarInsn(ALOAD,context.var("out"));
        mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"isSortField","()Z");
        mw.visitJumpInsn(IFNE,_else);
        mw.visitVarInsn(ALOAD,0);
        mw.visitVarInsn(ALOAD,1);
        mw.visitVarInsn(ALOAD,2);
        mw.visitVarInsn(ALOAD,3);
        mw.visitVarInsn(ALOAD,4);
        mw.visitVarInsn(ILOAD,5);
        mw.visitMethodInsn(INVOKEVIRTUAL,classNameType,"writeUnsorted","(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        mw.visitInsn(RETURN);
        mw.visitLabel(_else);
      }
    }
    if (context.writeDirect && !nonContext) {
      Label _direct=new Label();
      Label _directElse=new Label();
      mw.visitVarInsn(ALOAD,0);
      mw.visitVarInsn(ALOAD,Context.serializer);
      mw.visitMethodInsn(INVOKEVIRTUAL,JavaBeanSerializer,"writeDirect","(L" + JSONSerializer + ";)Z");
      mw.visitJumpInsn(IFNE,_directElse);
      mw.visitVarInsn(ALOAD,0);
      mw.visitVarInsn(ALOAD,1);
      mw.visitVarInsn(ALOAD,2);
      mw.visitVarInsn(ALOAD,3);
      mw.visitVarInsn(ALOAD,4);
      mw.visitVarInsn(ILOAD,5);
      mw.visitMethodInsn(INVOKEVIRTUAL,classNameType,"writeNormal","(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
      mw.visitInsn(RETURN);
      mw.visitLabel(_directElse);
      mw.visitVarInsn(ALOAD,context.var("out"));
      mw.visitLdcInsn(SerializerFeature.DisableCircularReferenceDetect.mask);
      mw.visitMethodInsn(INVOKEVIRTUAL,SerializeWriter,"isEnabled","(I)Z");
      mw.visitJumpInsn(IFEQ,_direct);
      mw.visitVarInsn(ALOAD,0);
      mw.visitVarInsn(ALOAD,1);
      mw.visitVarInsn(ALOAD,2);
      mw.visitVarInsn(ALOAD,3);
      mw.visitVarInsn(ALOAD,4);
      mw.visitVarInsn(ILOAD,5);
      mw.visitMethodInsn(INVOKEVIRTUAL,classNameType,"writeDirectNonContext","(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
      mw.visitInsn(RETURN);
      mw.visitLabel(_direct);
    }
    mw.visitVarInsn(ALOAD,Context.obj);
    mw.visitTypeInsn(CHECKCAST,type(clazz));
    mw.visitVarInsn(ASTORE,context.var("entity"));
    generateWriteMethod(clazz,mw,getters,context);
    mw.visitInsn(RETURN);
    mw.visitMaxs(7,context.variantIndex + 2);
    mw.visitEnd();
  }
  if (!nativeSorted) {
    Context context=new Context(getters,beanInfo,classNameType,false,DisableCircularReferenceDetect);
    mw=new MethodWriter(cw,ACC_PUBLIC,"writeUnsorted","(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V",null,new String[]{"java/io/IOException"});
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitFieldInsn(GETFIELD,JSONSerializer,"out",SerializeWriter_desc);
    mw.visitVarInsn(ASTORE,context.var("out"));
    mw.visitVarInsn(ALOAD,Context.obj);
    mw.visitTypeInsn(CHECKCAST,type(clazz));
    mw.visitVarInsn(ASTORE,context.var("entity"));
    generateWriteMethod(clazz,mw,unsortedGetters,context);
    mw.visitInsn(RETURN);
    mw.visitMaxs(7,context.variantIndex + 2);
    mw.visitEnd();
  }
  for (int i=0; i < 3; ++i) {
    String methodName;
    boolean nonContext=DisableCircularReferenceDetect;
    boolean writeDirect=false;
    if (i == 0) {
      methodName="writeAsArray";
      writeDirect=true;
    }
 else     if (i == 1) {
      methodName="writeAsArrayNormal";
    }
 else {
      writeDirect=true;
      nonContext=true;
      methodName="writeAsArrayNonContext";
    }
    Context context=new Context(getters,beanInfo,classNameType,writeDirect,nonContext);
    mw=new MethodWriter(cw,ACC_PUBLIC,methodName,"(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V",null,new String[]{"java/io/IOException"});
    mw.visitVarInsn(ALOAD,Context.serializer);
    mw.visitFieldInsn(GETFIELD,JSONSerializer,"out",SerializeWriter_desc);
    mw.visitVarInsn(ASTORE,context.var("out"));
    mw.visitVarInsn(ALOAD,Context.obj);
    mw.visitTypeInsn(CHECKCAST,type(clazz));
    mw.visitVarInsn(ASTORE,context.var("entity"));
    generateWriteAsArray(clazz,mw,getters,context);
    mw.visitInsn(RETURN);
    mw.visitMaxs(7,context.variantIndex + 2);
    mw.visitEnd();
  }
  byte[] code=cw.toByteArray();
  Class<?> serializerClass=classLoader.defineClassPublic(classNameFull,code,0,code.length);
  Constructor<?> constructor=serializerClass.getConstructor(SerializeBeanInfo.class);
  Object instance=constructor.newInstance(beanInfo);
  return (JavaBeanSerializer)instance;
}
