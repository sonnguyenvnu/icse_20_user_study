public static void main(String... args){
  final Function<String,SNode> f=(  String name) -> {
    jetbrains.mps.smodel.SNode node=new jetbrains.mps.smodel.SNode(SNodeUtil.concept_BaseConcept);
    node.setProperty(SNodeUtil.property_INamedConcept_name,name);
    return node;
  }
;
  SNode root=f.apply("0");
  SNode c1, c2, c3;
  root.addChild(SNodeUtil.link_BaseConcept_smodelAttribute,c1=f.apply("1"));
  root.addChild(SNodeUtil.link_BaseConcept_smodelAttribute,c2=f.apply("2"));
  root.addChild(SNodeUtil.link_BaseConcept_smodelAttribute,c3=f.apply("3"));
  IntStream.rangeClosed(4,10).mapToObj(String::valueOf).map(f).forEach(node -> c1.addChild(SNodeUtil.link_BaseConcept_smodelAttribute,node));
  IntStream.rangeClosed(11,15).mapToObj(String::valueOf).map(f).forEach(node -> c2.addChild(SNodeUtil.link_BaseConcept_smodelAttribute,node));
  IntStream.rangeClosed(16,17).mapToObj(String::valueOf).map(f).forEach(node -> c3.addChild(SNodeUtil.link_BaseConcept_smodelAttribute,node));
  new ConsistentNodeIdentityHelper(null).processTree(root);
  Consumer<SNode> check1=(  SNode n) -> {
    String name=n.getProperty(SNodeUtil.property_INamedConcept_name);
    if (name == null || !name.equals(n.getNodeId().toString())) {
      throw new AssertionError("name:" + name + ", nodeId:" + n.getNodeId());
    }
  }
;
  long count=StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DescendantsTreeIterator(root),0),false).count();
  if (18 != count) {
    throw new AssertionError("Expected 18, was " + count);
  }
  StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DescendantsTreeIterator(root),0),false).forEach(check1);
}
