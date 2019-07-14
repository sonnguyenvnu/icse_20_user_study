/** 
 * Inspects  {@link ScopeData} parameters for given method parameter information.
 */
public ScopeData inspectMethodParameterScopes(final String name,final Class type,final Annotation[] annotations){
  In in=null;
  Out out=null;
  for (  final Annotation annotation : annotations) {
    if (annotation instanceof In) {
      in=(In)annotation;
    }
 else     if (annotation instanceof Out) {
      out=(Out)annotation;
    }
  }
  final Class<? extends MadvocScope> scope=resolveScopeClassFromAnnotations(annotations);
  int count=0;
  InjectionPoint[] ins=null;
  InjectionPoint[] outs=null;
  if (in != null) {
    final InjectionPoint scopeDataIn=buildInjectionPoint(in.value(),in.defaultValue(),name,type,scope);
    if (scopeDataIn != null) {
      count++;
      ins=new InjectionPoint[]{scopeDataIn};
    }
  }
  if (out != null) {
    final InjectionPoint scopeDataOut=buildInjectionPoint(out.value(),null,name,type,scope);
    if (scopeDataOut != null) {
      count++;
      outs=new InjectionPoint[]{scopeDataOut};
    }
  }
  if (count == 0) {
    return NO_SCOPE_DATA;
  }
  return new ScopeData(this,ins,outs);
}
