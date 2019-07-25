/** 
 * Creates a Groovy context from a context.
 * @param ctx the actual context
 * @return a Groovy context
 */
static GroovyContext from(Context ctx){
  if (ctx instanceof GroovyContext) {
    return (GroovyContext)ctx;
  }
 else {
    return new DefaultGroovyContext(ctx);
  }
}
