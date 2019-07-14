@SuppressWarnings("unchecked") public T legalPackages(String[] packs){
  if (packs != null) {
    this.legalPackageNames=Arrays.copyOf(packs,packs.length);
  }
  return (T)this;
}
