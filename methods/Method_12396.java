public Endpoints withEndpoint(String id,String url){
  Endpoint endpoint=Endpoint.of(id,url);
  HashMap<String,Endpoint> newEndpoints=new HashMap<>(this.endpoints);
  newEndpoints.put(endpoint.getId(),endpoint);
  return new Endpoints(newEndpoints.values());
}
