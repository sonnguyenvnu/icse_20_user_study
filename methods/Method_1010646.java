@Override public boolean solve(SNode node,Set<SNode> leftTypes,Set<SNode> rightTypes,State state,Map<SNode,RelationBlock> typesToBlocks){
  List<SNode> nodes=new LinkedList<>();
  nodes.addAll(leftTypes);
  nodes.addAll(rightTypes);
  if (nodes.isEmpty()) {
    return false;
  }
  SubTypingManagerNew subTypingManager=(SubTypingManagerNew)TypeChecker.getInstance().getSubtypingManager();
  nodes=SubtypingUtil.eliminateSuperTypes(nodes);
  List<SNode> types=new LinkedList<>();
  SNode result=null;
  for (  SNode type : nodes) {
    for (    SNode toCompare : types) {
      if (!subTypingManager.isComparable(type,toCompare,true)) {
        result=type;
      }
    }
    types.add(type);
  }
  if (result == null) {
    result=createMeet(nodes);
  }
  if (result != null) {
    RelationBlock block=typesToBlocks.get(result);
    EquationInfo info=(block != null) ? block.getEquationInfo() : typesToBlocks.get(nodes.iterator().next()).getEquationInfo();
    state.addEquation(node,result,info);
  }
  return result != null;
}
