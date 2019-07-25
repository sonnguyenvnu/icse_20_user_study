/** 
 * Creates a method.  <code>modifiers</code> can contain <code>Modifier.STATIC</code>.
 * @param modifiers         access modifiers.
 * @param returnType        the type of the returned value.
 * @param mname             the method name.
 * @param parameters        a list of the parameter types.
 * @param exceptions        a list of the exception types.
 * @param body              the source text of the method body.It must be a block surrounded by <code>{}</code>. If it is <code>null</code>, the created method does nothing except returning zero or null.
 * @param declaring    the class to which the created method is added.
 * @see Modifier
 */
public static CtMethod make(int modifiers,CtClass returnType,String mname,CtClass[] parameters,CtClass[] exceptions,String body,CtClass declaring) throws CannotCompileException {
  try {
    CtMethod cm=new CtMethod(returnType,mname,parameters,declaring);
    cm.setModifiers(modifiers);
    cm.setExceptionTypes(exceptions);
    cm.setBody(body);
    return cm;
  }
 catch (  NotFoundException e) {
    throw new CannotCompileException(e);
  }
}
