@Override public final CostModelNode get(final SemanticNode eon){
  if (eon == this.node || !(eon instanceof OpApplNode)) {
    return this;
  }
  CostModelNode child=children.get(eon);
  if (child != null) {
    return child;
  }
  if (recursive != null) {
    child=recursive.children.get(eon);
    if (child != null) {
      return child;
    }
  }
  if (seen.add(eon.myUID)) {
    MP.printMessage(EC.TLC_COVERAGE_MISMATCH,new String[]{eon.toString(),this.toString()});
  }
  return this;
}
