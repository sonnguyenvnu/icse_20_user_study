public AnnotatedMethod find(Method m){
  if (_methods == null) {
    return null;
  }
  return _methods.get(new MemberKey(m));
}
