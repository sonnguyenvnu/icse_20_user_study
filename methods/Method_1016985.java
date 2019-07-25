@Override public List<ParameterSpecification> options(){
  return ImmutableList.of(parameter("zookeeper","Connection string to Zookeeper","<url>[,..][/prefix]"),parameter("group","Consumer Group","<group>"),parameter("topics","Topics to consume from","<topic>[,..]"),parameter("schema","Schema Class to use","<schema>"));
}
