@Override protected boolean iteration(List<RelationBlock> inequalities){
  return super.iteration(inequalities) && !getState().isTargetTypeCalculated();
}
