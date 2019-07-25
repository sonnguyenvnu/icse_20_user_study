/** 
 * Modifies the bodies of all methods and constructors declared in the class.  This method calls <code>instrument()</code> on every <code>CtMethod</code> and <code>CtConstructor</code> object in the class.
 * @param editor            specifies how to modify.
 */
public void instrument(ExprEditor editor) throws CannotCompileException {
  checkModify();
}
