public static List<SNode> roots(SModel model,final SAbstractConcept concept){
  if (model == null) {
    return Collections.emptyList();
  }
  List<SNode> list=new ArrayList<SNode>();
  Iterable<SNode> iterable=model.getRootNodes();
  if (concept != null) {
    Condition<SNode> cond=new InstanceOfCondition(concept).tolerateNulls();
    iterable=new ConditionalIterable<SNode>(iterable,cond);
  }
  for (  SNode node : iterable) {
    list.add(node);
  }
  return list;
}
