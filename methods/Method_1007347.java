/** 
 * Modifies the method/constructor body. <p>While executing this method, only <code>replace()</code> in <code>Expr</code> is available for bytecode modification. Other methods such as <code>insertBefore()</code> may collapse the bytecode because the <code>ExprEditor</code> loses its current position.
 * @param editor            specifies how to modify.
 * @see javassist.expr.Expr#replace(String)
 * @see #insertBefore(String)
 */
public void instrument(ExprEditor editor) throws CannotCompileException {
  if (declaringClass.isFrozen())   declaringClass.checkModify();
  if (editor.doit(declaringClass,methodInfo))   declaringClass.checkModify();
}
