@Override public List<Object> apply(Object o){
  if (o.getClass().equals(typeToFind)) {
    results.add(o);
  }
  return null;
}
