@Override public List<Object> apply(Object o){
  if (o instanceof Tc) {
    tcList.add((Tc)o);
  }
  return null;
}
