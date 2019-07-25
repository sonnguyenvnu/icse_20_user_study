@Override public Runnable apply(Runnable t) throws Exception {
  Runnable[] ref={t};
  forEach(ref,this);
  return ref[0];
}
