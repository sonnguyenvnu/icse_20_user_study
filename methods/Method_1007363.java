/** 
 * Creates a public setter method.  The setter method assigns the value of the first parameter to the specified field in the class to which this method is added. The created method is not static even if the field is static.  You may not change it to be static by <code>setModifiers()</code> in <code>CtBehavior</code>.
 * @param methodName        the name of the setter
 * @param field             the field accessed.
 */
public static CtMethod setter(String methodName,CtField field) throws CannotCompileException {
  FieldInfo finfo=field.getFieldInfo2();
  String fieldType=finfo.getDescriptor();
  String desc="(" + fieldType + ")V";
  ConstPool cp=finfo.getConstPool();
  MethodInfo minfo=new MethodInfo(cp,methodName,desc);
  minfo.setAccessFlags(AccessFlag.PUBLIC);
  Bytecode code=new Bytecode(cp,3,3);
  try {
    String fieldName=finfo.getName();
    if ((finfo.getAccessFlags() & AccessFlag.STATIC) == 0) {
      code.addAload(0);
      code.addLoad(1,field.getType());
      code.addPutfield(Bytecode.THIS,fieldName,fieldType);
    }
 else {
      code.addLoad(1,field.getType());
      code.addPutstatic(Bytecode.THIS,fieldName,fieldType);
    }
    code.addReturn(null);
  }
 catch (  NotFoundException e) {
    throw new CannotCompileException(e);
  }
  minfo.setCodeAttribute(code.toCodeAttribute());
  CtClass cc=field.getDeclaringClass();
  return new CtMethod(minfo,cc);
}
