@Override public List<ParameterSpecification> options(){
  return ImmutableList.of(parameter("pattern","Index pattern to use (example: heroic-%s)","<pattern>"),parameter("clusterName","Cluster name to connect to","<string>"),parameter("seeds","Seeds to connect to","<host>[:<port][,..]"),parameter("type","Backend type to use, available types are: " + arguments.join(ElasticsearchSuggestModule.types()),"<type>"));
}
