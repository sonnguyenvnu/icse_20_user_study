static String endpoint(String index,String type,String id){
  return new EndpointBuilder().addPathPart(index,type,id).build();
}
