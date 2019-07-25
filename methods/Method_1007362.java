/** 
 * Creates a public getter method.  The getter method returns the value of the specified field in the class to which this method is added. The created method is initially not static even if the field is static.  Change the modifiers if the method should be static.
 * @param methodName        the name of the getter
 * @param field             the field accessed.
 */
public static CtMethod getter(String methodName,CtField field) throws CannotCompileException {
  FieldInfo finfo=field.getFieldInfo2();
  String fieldType=finfo.getDescriptor();
  String desc="()" + fieldType;
  ConstPool cp=finfo.getConstPool();
  MethodInfo minfo=new MethodInfo(cp,methodName,desc);
  minfo.setAccessFlags(AccessFlag.PUBLIC);
  Bytecode code=new Bytecode(cp,2,1);
  try {
    String fieldName=finfo.getName();
    if ((finfo.getAccessFlags() & AccessFlag.STATIC) == 0) {
      code.addAload(0);
      code.addGetfield(Bytecode.THIS,fieldName,fieldType);
    }
 else     code.addGetstatic(Bytecode.THIS,fieldName,fieldType);
    code.addReturn(field.getType());
  }
 catch (  NotFoundException e) {
    throw new CannotCompileException(e);
  }
  minfo.setCodeAttribute(code.toCodeAttribute());
  CtClass cc=field.getDeclaringClass();
  return new CtMethod(minfo,cc);
}
