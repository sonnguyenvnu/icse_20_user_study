@Override public SNode coerce_(final SNode subtype,final IMatchingPattern pattern,final TypeCheckingContext typeCheckingContext){
  return myTypeChecker.computeWithTrace(() -> RuntimeSupport_Tracer.super.coerce_(subtype,pattern,typeCheckingContext),"coerce");
}
