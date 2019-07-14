@Override public Set<ReconItemIdValue> visit(EntityIdValue value){
  if (ReconItemIdValue.class.isInstance(value)) {
    ReconItemIdValue recon=(ReconItemIdValue)value;
    if (recon.isNew()) {
      return Collections.singleton(recon);
    }
  }
  return null;
}
