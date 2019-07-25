/** 
 * Creates a Groovy chain wrapper over a chain instance.
 * @param chain a chain instance
 * @return a Groovy wrapper
 */
static GroovyChain from(Chain chain){
  if (chain instanceof GroovyChain) {
    return (GroovyChain)chain;
  }
 else {
    return new DefaultGroovyChain(chain);
  }
}
