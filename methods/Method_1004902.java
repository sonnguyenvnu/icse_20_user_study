@Override public void lock(){
  if (null != groups) {
    groups=Collections.unmodifiableSet(groups);
  }
}
