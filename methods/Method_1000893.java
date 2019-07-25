private final AnnotatedMethodMap _methods(){
  AnnotatedMethodMap m=_memberMethods;
  if (m == null) {
    if (_type == null) {
      m=new AnnotatedMethodMap();
    }
 else {
      m=AnnotatedMethodCollector.collectMethods(_annotationIntrospector,this,_mixInResolver,_typeFactory,_type,_superTypes,_primaryMixIn);
    }
    _memberMethods=m;
  }
  return m;
}
