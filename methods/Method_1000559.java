public Set<String> names(){
  if (objs == null)   return Collections.emptySet();
synchronized (this) {
    return new HashSet<String>(objs.keySet());
  }
}
