/** 
 * Resolves tx scope from scope pattern.
 */
public String resolveScope(final Class type,final String methodName){
  if (scopePattern == null) {
    return null;
  }
  String ctx=scopePattern;
  ctx=StringUtil.replace(ctx,JTXCTX_PATTERN_CLASS,type.getName());
  ctx=StringUtil.replace(ctx,JTXCTX_PATTERN_METHOD,methodName);
  return ctx;
}
