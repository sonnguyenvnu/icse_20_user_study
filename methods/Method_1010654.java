protected boolean iteration(List<RelationBlock> inequalities){
  if (myNodesInc.size() == 0) {
    return false;
  }
  mySolveOnlyRight=true;
  if (chooseVarAndSolve(mySolvableRight))   return true;
  mySolveOnlyRight=false;
  if (chooseVarAndSolve(mySolvableLeft))   return true;
  if (trySolvingRecursive(inequalities))   return true;
  return lastChance(inequalities);
}
