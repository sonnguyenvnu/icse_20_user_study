/** 
 * Modifies the method/constructor body.
 * @param converter         specifies how to modify.
 */
public void instrument(CodeConverter converter) throws CannotCompileException {
  declaringClass.checkModify();
  ConstPool cp=methodInfo.getConstPool();
  converter.doit(getDeclaringClass(),methodInfo,cp);
}
