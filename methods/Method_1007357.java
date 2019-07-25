/** 
 * Creates a public constructor.
 * @param parameters        a list of the parameter types.
 * @param exceptions        a list of the exception types.
 * @param body              the source text of the constructor body.It must be a block surrounded by <code>{}</code>. If it is <code>null</code>, the substituted constructor body does nothing except calling <code>super()</code>.
 * @param declaring    the class to which the created method is added.
 */
public static CtConstructor make(CtClass[] parameters,CtClass[] exceptions,String body,CtClass declaring) throws CannotCompileException {
  try {
    CtConstructor cc=new CtConstructor(parameters,declaring);
    cc.setExceptionTypes(exceptions);
    cc.setBody(body);
    return cc;
  }
 catch (  NotFoundException e) {
    throw new CannotCompileException(e);
  }
}
