public AnnotatedMethod find(String name,Class<?>[] paramTypes){
  if (_methods == null) {
    return null;
  }
  return _methods.get(new MemberKey(name,paramTypes));
}
