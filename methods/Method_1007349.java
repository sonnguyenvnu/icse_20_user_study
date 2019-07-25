/** 
 * Applies the given converter to all methods and constructors declared in the class.  This method calls <code>instrument()</code> on every <code>CtMethod</code> and <code>CtConstructor</code> object in the class.
 * @param converter         specifies how to modify.
 */
public void instrument(CodeConverter converter) throws CannotCompileException {
  checkModify();
}
