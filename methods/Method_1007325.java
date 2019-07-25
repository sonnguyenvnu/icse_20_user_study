/** 
 * Compiles a method, constructor, or field declaration to a class. A field declaration can declare only one field. <p>In a method or constructor body, $0, $1, ... and $_ are not available.
 * @return          a <code>CtMethod</code>, <code>CtConstructor</code>,or <code>CtField</code> object.
 * @see #recordProceed(String,String)
 */
public CtMember compile(String src) throws CompileError {
  Parser p=new Parser(new Lex(src));
  ASTList mem=p.parseMember1(stable);
  try {
    if (mem instanceof FieldDecl)     return compileField((FieldDecl)mem);
    CtBehavior cb=compileMethod(p,(MethodDecl)mem);
    CtClass decl=cb.getDeclaringClass();
    cb.getMethodInfo2().rebuildStackMapIf6(decl.getClassPool(),decl.getClassFile2());
    return cb;
  }
 catch (  BadBytecode bb) {
    throw new CompileError(bb.getMessage());
  }
catch (  CannotCompileException e) {
    throw new CompileError(e.getMessage());
  }
}
