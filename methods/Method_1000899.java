AnnotatedMethodMap collect(TypeResolutionContext tc,TypeFactory typeFactory,JavaType mainType,List<JavaType> superTypes,Class<?> primaryMixIn){
  Map<MemberKey,MethodBuilder> methods=new LinkedHashMap<>();
  _addMemberMethods(tc,mainType.getRawClass(),methods,primaryMixIn);
  for (  JavaType type : superTypes) {
    Class<?> mixin=(_mixInResolver == null) ? null : _mixInResolver.findMixInClassFor(type.getRawClass());
    _addMemberMethods(new TypeResolutionContext.Basic(typeFactory,type.getBindings()),type.getRawClass(),methods,mixin);
  }
  boolean checkJavaLangObject=false;
  if (_mixInResolver != null) {
    Class<?> mixin=_mixInResolver.findMixInClassFor(Object.class);
    if (mixin != null) {
      _addMethodMixIns(tc,mainType.getRawClass(),methods,mixin);
      checkJavaLangObject=true;
    }
  }
  if (checkJavaLangObject && (_intr != null) && !methods.isEmpty()) {
    for (    Map.Entry<MemberKey,MethodBuilder> entry : methods.entrySet()) {
      MemberKey k=entry.getKey();
      if (!"hashCode".equals(k.getName()) || (0 != k.argCount())) {
        continue;
      }
      try {
        Method m=Object.class.getDeclaredMethod(k.getName());
        if (m != null) {
          MethodBuilder b=entry.getValue();
          b.annotations=collectDefaultAnnotations(b.annotations,m.getDeclaredAnnotations());
          b.method=m;
        }
      }
 catch (      Exception e) {
      }
    }
  }
  if (methods.isEmpty()) {
    return new AnnotatedMethodMap();
  }
  Map<MemberKey,AnnotatedMethod> actual=new LinkedHashMap<>(methods.size());
  for (  Map.Entry<MemberKey,MethodBuilder> entry : methods.entrySet()) {
    AnnotatedMethod am=entry.getValue().build();
    if (am != null) {
      actual.put(entry.getKey(),am);
    }
  }
  return new AnnotatedMethodMap(actual);
}
