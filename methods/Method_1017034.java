@Override public Filter optimize(){
  return optimize(flatten(this.filters()));
}
