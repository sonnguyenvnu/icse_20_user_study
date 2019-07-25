Creators collect(JavaType type,Class<?> primaryMixIn){
  List<AnnotatedConstructor> constructors=_findPotentialConstructors(type,primaryMixIn);
  List<AnnotatedMethod> factories=_findPotentialFactories(type,primaryMixIn);
  if (_intr != null) {
    if (_defaultConstructor != null) {
      if (_intr.hasIgnoreMarker(_defaultConstructor)) {
        _defaultConstructor=null;
      }
    }
    for (int i=constructors.size(); --i >= 0; ) {
      if (_intr.hasIgnoreMarker(constructors.get(i))) {
        constructors.remove(i);
      }
    }
    for (int i=factories.size(); --i >= 0; ) {
      if (_intr.hasIgnoreMarker(factories.get(i))) {
        factories.remove(i);
      }
    }
  }
  return new AnnotatedClass.Creators(_defaultConstructor,constructors,factories);
}
