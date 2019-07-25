/** 
 * ????FastClass??: ????, ????????. ??????returnType???null. ???????,returnType??null, ???????,returnType???null, ??Void??????.
 * @param klass ?????
 * @param mod ???modify??
 * @param params ????
 * @param method asm??????
 * @param returnType ???. ??????????,????null,??????null.
 * @param className ????,FastClass??????
 * @param descriptor ???
 */
public static byte[] _make(Class<?> klass,int mod,Class<?>[] params,org.nutz.repo.org.objectweb.asm.commons.Method method,Class<?> returnType,String className,String descriptor){
  ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES);
  cw.visit(V1_5,ACC_PUBLIC,className.replace('.','/'),null,"java/lang/Object",new String[]{FastMethod.class.getName().replace('.','/')});
  Type klassType=Type.getType(klass);
  addConstructor(cw,Type.getType(Object.class),org.nutz.repo.org.objectweb.asm.commons.Method.getMethod("void <init> ()"));
  GeneratorAdapter mg=new GeneratorAdapter(ACC_PUBLIC,_M,null,new Type[]{Exception_TYPE},cw);
  if (returnType == null) {
    mg.newInstance(klassType);
    mg.dup();
  }
 else   if (!Modifier.isStatic(mod)) {
    mg.loadThis();
    mg.loadArg(0);
    mg.checkCast(klassType);
  }
  if (params.length > 0) {
    for (int i=0; i < params.length; i++) {
      mg.loadArg(1);
      mg.push(i);
      mg.arrayLoad(Type.getType(Object.class));
      Type paramType=Type.getType(params[i]);
      if (params[i].isPrimitive()) {
        mg.unbox(paramType);
      }
 else {
        mg.checkCast(paramType);
      }
    }
  }
  if (returnType == null) {
    mg.invokeConstructor(klassType,method);
  }
 else {
    if (Modifier.isStatic(mod)) {
      mg.invokeStatic(klassType,method);
    }
 else     if (Modifier.isInterface(klass.getModifiers())) {
      mg.invokeInterface(klassType,method);
    }
 else {
      mg.invokeVirtual(klassType,method);
    }
    if (Void.class.equals(returnType)) {
      mg.visitInsn(ACONST_NULL);
    }
 else     if (returnType.isPrimitive()) {
      mg.box(Type.getType(returnType));
    }
 else {
    }
  }
  Label tmp=new Label();
  mg.visitLabel(tmp);
  mg.visitLineNumber(1,tmp);
  mg.returnValue();
  mg.endMethod();
  cw.visitSource(klass.getSimpleName() + ".java",null);
  cw.visitEnd();
  return cw.toByteArray();
}
