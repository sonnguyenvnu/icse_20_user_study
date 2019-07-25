public Group subtract(Group other){
  final HashSet<TemplateMappingConfiguration> mc=new HashSet<>(myMappings);
  mc.removeAll(other.myMappings);
  return new Group(mc,myIsTopPriority);
}
