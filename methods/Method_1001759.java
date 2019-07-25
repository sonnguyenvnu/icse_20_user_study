static RealLine line(Collection<Real> reals){
  return ((AbstractReal)reals.iterator().next()).getLine();
}
