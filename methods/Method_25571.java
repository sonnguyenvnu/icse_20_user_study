/** 
 * Finds a declaration with the given name and type that is in scope at the current location. 
 */
@Nullable public static Symbol findIdent(String name,VisitorState state,KindSelector kind){
  ClassType enclosingClass=ASTHelpers.getType(state.findEnclosing(ClassTree.class));
  if (enclosingClass == null || enclosingClass.tsym == null) {
    return null;
  }
  Env<AttrContext> env=Enter.instance(state.context).getClassEnv(enclosingClass.tsym);
  MethodTree enclosingMethod=state.findEnclosing(MethodTree.class);
  if (enclosingMethod != null) {
    env=MemberEnter.instance(state.context).getMethodEnv((JCMethodDecl)enclosingMethod,env);
  }
  try {
    Method method=Resolve.class.getDeclaredMethod("findIdent",Env.class,Name.class,KindSelector.class);
    method.setAccessible(true);
    Symbol result=(Symbol)method.invoke(Resolve.instance(state.context),env,state.getName(name),kind);
    return result.exists() ? result : null;
  }
 catch (  ReflectiveOperationException e) {
    throw new LinkageError(e.getMessage(),e);
  }
}
