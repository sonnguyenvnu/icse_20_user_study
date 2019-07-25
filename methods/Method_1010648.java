@Override public boolean solve(SNode node,Set<SNode> leftTypes,Set<SNode> rightTypes,State state,Map<SNode,RelationBlock> typesToBlocks){
  SNode result=null;
  EquationInfo info=null;
  if (!leftTypes.isEmpty()) {
    final LinkedList<SNode> leftTypesList=new LinkedList<>();
    for (    SNode lt : leftTypes) {
      if (LatticeUtil.isMeet(lt)) {
        lt=TypesUtil.cleanupMeet(lt);
      }
      leftTypesList.add(lt);
    }
    result=SubtypingUtil.createLeastCommonSupertype(leftTypesList,state.getTypeCheckingContext());
    if (LatticeUtil.isMeet(result)) {
      result=TypesUtil.cleanupMeet(result);
    }
    RelationBlock block=typesToBlocks.get(result);
    info=(block != null) ? block.getEquationInfo() : typesToBlocks.get(leftTypes.iterator().next()).getEquationInfo();
  }
 else   if (!rightTypes.isEmpty()) {
    result=createMeet(rightTypes);
    RelationBlock block=typesToBlocks.get(result);
    info=(block != null) ? block.getEquationInfo() : typesToBlocks.get(rightTypes.iterator().next()).getEquationInfo();
  }
  if (TypesUtil.isVariable(result) && TypesUtil.isVariable(node) && result.getName().equals(node.getName()))   return false;
  return result != null && state.addEquation(node,result,info);
}
