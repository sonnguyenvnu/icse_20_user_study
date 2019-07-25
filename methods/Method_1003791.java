default GroovyRatpackServerSpec handlers(@DelegatesTo(value=GroovyChain.class,strategy=Closure.DELEGATE_FIRST) Closure<?> handlers){
  return handler(r -> Handlers.chain(r,Groovy.chainAction(handlers)));
}
