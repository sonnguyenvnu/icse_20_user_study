@Override public List<ParameterSpecification> options(){
  return ImmutableList.of(parameter("discovery","Discovery method to use, valid are: static, srv","<type>"),parameter("host","Host to add to list of static nodes to discover, can be " + "used multiple times","<uri>"),parameter("record","Record to add to lookup through SRV, can be used " + "multiple times","<srv>"),parameter("protocol","Protocol to use default: " + DEFAULT_PROTOCOL,"<protocol>"),parameter("port","Port to use for looked up SRV records, default: 1394","<port>"));
}
