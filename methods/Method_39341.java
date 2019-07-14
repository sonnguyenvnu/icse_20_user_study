/** 
 * Delegates to  {@link jodd.petite.scope.Scope#register(jodd.petite.BeanDefinition,Object)}if scope is defined.
 */
protected void scopeRegister(final Object object){
  if (scope != null) {
    scope.register(this,object);
  }
}
