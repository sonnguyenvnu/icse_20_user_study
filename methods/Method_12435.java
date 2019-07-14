@Override protected Mono<Void> doNotify(InstanceEvent event,Instance instance){
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  String auth=Base64Utils.encodeToString(String.format("%s:%s",token,username).getBytes(StandardCharsets.UTF_8));
  headers.add(HttpHeaders.AUTHORIZATION,String.format("Basic %s",auth));
  return Mono.fromRunnable(() -> restTemplate.exchange(createUrl(),HttpMethod.POST,new HttpEntity<>(createMessage(event,instance),headers),Void.class));
}
