@Override public <T>T compile(String scriptName,String scriptSource,ScriptContext<T> context,Map<String,String> params){
  final SecurityManager sm=System.getSecurityManager();
  SpecialPermission.check();
  Expression expr=AccessController.doPrivileged(new PrivilegedAction<Expression>(){
    @Override public Expression run(){
      try {
        AccessControlContext engineContext=AccessController.getContext();
        ClassLoader loader=getClass().getClassLoader();
        if (sm != null) {
          loader=new ClassLoader(loader){
            @Override protected Class<?> loadClass(            String name,            boolean resolve) throws ClassNotFoundException {
              try {
                engineContext.checkPermission(new ClassPermission(name));
              }
 catch (              SecurityException e) {
                throw new ClassNotFoundException(name,e);
              }
              return super.loadClass(name,resolve);
            }
          }
;
        }
        return JavascriptCompiler.compile(scriptSource,JavascriptCompiler.DEFAULT_FUNCTIONS,loader);
      }
 catch (      ParseException e) {
        throw convertToScriptException("compile error",scriptSource,scriptSource,e);
      }
    }
  }
);
  if (context.instanceClazz.equals(SearchScript.class)) {
    SearchScript.Factory factory=(p,lookup) -> newSearchScript(expr,lookup,p);
    return context.factoryClazz.cast(factory);
  }
 else   if (context.instanceClazz.equals(ExecutableScript.class)) {
    ExecutableScript.Factory factory=(p) -> new ExpressionExecutableScript(expr,p);
    return context.factoryClazz.cast(factory);
  }
 else   if (context.instanceClazz.equals(FilterScript.class)) {
    FilterScript.Factory factory=(p,lookup) -> newFilterScript(expr,lookup,p);
    return context.factoryClazz.cast(factory);
  }
  throw new IllegalArgumentException("expression engine does not know how to handle script context [" + context.name + "]");
}
