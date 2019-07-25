default GroovyChain when(@DelegatesTo(value=GroovyContext.class,strategy=Closure.DELEGATE_FIRST) Closure<?> test,Action<? super Chain> chain) throws Exception {
  return when(ctx -> {
    final GroovyContext groovyContext=GroovyContext.from(ctx);
    return DefaultGroovyMethods.asBoolean(ClosureUtil.cloneAndSetDelegate(groovyContext,test,Closure.DELEGATE_FIRST).isCase(groovyContext));
  }
,chain);
}
