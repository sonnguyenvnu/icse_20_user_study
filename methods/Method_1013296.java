public CostModel report(){
  if (relation == Relation.PROP) {
    assert getEvalCount() == 0L && this.secondary.getCount() == 0L;
    MP.printMessage(EC.TLC_COVERAGE_PROPERTY,new String[]{printLocation()});
  }
 else   if (relation == Relation.INIT) {
    MP.printMessage(EC.TLC_COVERAGE_INIT,new String[]{printLocation(),String.valueOf(getEvalCount()),String.valueOf(getEvalCount() + this.secondary.getCount())});
  }
 else {
    MP.printMessage(EC.TLC_COVERAGE_NEXT,new String[]{printLocation(),String.valueOf(this.secondary.getCount()),String.valueOf(getEvalCount())});
  }
  assert !(this.action.pred instanceof SubstInNode) ? this.children.size() == 1 : !this.children.isEmpty();
  this.children.values().forEach(c -> c.report());
  return this;
}
