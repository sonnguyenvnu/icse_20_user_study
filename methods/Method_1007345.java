/** 
 * @param isCons        true if this is a constructor.
 */
void copy(CtBehavior src,boolean isCons,ClassMap map) throws CannotCompileException {
  CtClass declaring=declaringClass;
  MethodInfo srcInfo=src.methodInfo;
  CtClass srcClass=src.getDeclaringClass();
  ConstPool cp=declaring.getClassFile2().getConstPool();
  map=new ClassMap(map);
  map.put(srcClass.getName(),declaring.getName());
  try {
    boolean patch=false;
    CtClass srcSuper=srcClass.getSuperclass();
    CtClass destSuper=declaring.getSuperclass();
    String destSuperName=null;
    if (srcSuper != null && destSuper != null) {
      String srcSuperName=srcSuper.getName();
      destSuperName=destSuper.getName();
      if (!srcSuperName.equals(destSuperName))       if (srcSuperName.equals(CtClass.javaLangObject))       patch=true;
 else       map.putIfNone(srcSuperName,destSuperName);
    }
    methodInfo=new MethodInfo(cp,srcInfo.getName(),srcInfo,map);
    if (isCons && patch)     methodInfo.setSuperclass(destSuperName);
  }
 catch (  NotFoundException e) {
    throw new CannotCompileException(e);
  }
catch (  BadBytecode e) {
    throw new CannotCompileException(e);
  }
}
