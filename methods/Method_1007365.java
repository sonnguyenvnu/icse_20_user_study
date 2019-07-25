public static CtMethod wrapped(CtClass returnType,String mname,CtClass[] parameterTypes,CtClass[] exceptionTypes,CtMethod body,ConstParameter constParam,CtClass declaring) throws CannotCompileException {
  CtMethod mt=new CtMethod(returnType,mname,parameterTypes,declaring);
  mt.setModifiers(body.getModifiers());
  try {
    mt.setExceptionTypes(exceptionTypes);
  }
 catch (  NotFoundException e) {
    throw new CannotCompileException(e);
  }
  Bytecode code=makeBody(declaring,declaring.getClassFile2(),body,parameterTypes,returnType,constParam);
  MethodInfo minfo=mt.getMethodInfo2();
  minfo.setCodeAttribute(code.toCodeAttribute());
  return mt;
}
