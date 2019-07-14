/** 
 * Inspects  {@link ScopeData} for given class. The results are not cached, so it shouldbe used only dyring configuration-time. For cached version, use  {@link #inspectClassScopesWithCache(Class)}.
 */
public ScopeData inspectClassScopes(final Class actionClass){
  final ClassDescriptor cd=ClassIntrospector.get().lookup(actionClass);
  final PropertyDescriptor[] allProperties=cd.getAllPropertyDescriptors();
  final List<InjectionPoint> listIn=new ArrayList<>(allProperties.length);
  final List<InjectionPoint> listOut=new ArrayList<>(allProperties.length);
  for (  final PropertyDescriptor pd : allProperties) {
    Class<? extends MadvocScope> scope=null;
    In in=null;
    Out out=null;
    if (pd.getFieldDescriptor() != null) {
      final Field field=pd.getFieldDescriptor().getField();
      in=field.getAnnotation(In.class);
      out=field.getAnnotation(Out.class);
      scope=resolveScopeClassFromAnnotations(field.getAnnotations());
    }
    if (pd.getWriteMethodDescriptor() != null) {
      final Method method=pd.getWriteMethodDescriptor().getMethod();
      if (in == null) {
        in=method.getAnnotation(In.class);
      }
      if (out == null) {
        out=method.getAnnotation(Out.class);
      }
      if (scope == null) {
        scope=resolveScopeClassFromAnnotations(method.getAnnotations());
      }
    }
    if (pd.getReadMethodDescriptor() != null) {
      final Method method=pd.getReadMethodDescriptor().getMethod();
      if (in == null) {
        in=method.getAnnotation(In.class);
      }
      if (out == null) {
        out=method.getAnnotation(Out.class);
      }
      if (scope == null) {
        scope=resolveScopeClassFromAnnotations(method.getAnnotations());
      }
    }
    final InjectionPoint ii=in == null ? null : buildInjectionPoint(in.value(),in.defaultValue(),pd.getName(),pd.getType(),scope);
    if (ii != null) {
      listIn.add(ii);
    }
    final InjectionPoint oi=out == null ? null : buildInjectionPoint(out.value(),null,pd.getName(),pd.getType(),scope);
    if (oi != null) {
      listOut.add(oi);
    }
  }
  if ((listIn.isEmpty()) && (listOut.isEmpty())) {
    return NO_SCOPE_DATA;
  }
  InjectionPoint[] in=null;
  InjectionPoint[] out=null;
  if (!listIn.isEmpty()) {
    in=listIn.toArray(new InjectionPoint[0]);
  }
  if (!listOut.isEmpty()) {
    out=listOut.toArray(new InjectionPoint[0]);
  }
  return new ScopeData(this,in,out);
}
