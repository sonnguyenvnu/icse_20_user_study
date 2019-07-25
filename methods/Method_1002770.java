/** 
 * Returns a single value resolver which retrieves a value from the specified  {@code getter}and converts it.
 */
private static BiFunction<AnnotatedValueResolver,ResolverContext,Object> resolver(Function<ResolverContext,String> getter){
  return (resolver,ctx) -> resolver.convert(getter.apply(ctx));
}
