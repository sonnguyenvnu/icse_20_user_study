public static List<SNode> nodes(SModel model,final SAbstractConcept concept){
  if (model == null) {
    return new ArrayList<SNode>();
  }
  if (concept != null) {
    return jetbrains.mps.smodel.SModelOperations.getNodes(model,concept);
  }
  List<SNode> result=new ArrayList<SNode>();
  for (  SNode node : SNodeUtil.getDescendants(model)) {
    result.add(node);
  }
  return result;
}
