@Override public List<ParameterSpecification> options(){
  return ImmutableList.of(parameter("host","Host to bind to","<host>"),parameter("port","Port to bind to","<port>"));
}
