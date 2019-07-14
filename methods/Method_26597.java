private Context prepareContext(Context baseContext,JCCompilationUnit compilationUnit){
  Context context=new SubContext(baseContext);
  if (context.get(JavaFileManager.class) == null) {
    JavacFileManager.preRegister(context);
  }
  context.put(JCCompilationUnit.class,compilationUnit);
  context.put(PackageSymbol.class,compilationUnit.packge);
  context.put(RULE_TYPE_VARS,typeVariables());
  return context;
}
