/** 
 * Creates a method from a <code>MethodInfo</code> object.
 * @param declaring     the class declaring the method.
 * @throws CannotCompileException       if the the <code>MethodInfo</code>object and the declaring class have different <code>ConstPool</code> objects
 * @since 3.6
 */
public static CtMethod make(MethodInfo minfo,CtClass declaring) throws CannotCompileException {
  if (declaring.getClassFile2().getConstPool() != minfo.getConstPool())   throw new CannotCompileException("bad declaring class");
  return new CtMethod(minfo,declaring);
}
