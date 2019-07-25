private void post(byte[] json){
  HttpHeaders httpHeaders=new HttpHeaders();
  httpHeaders.setContentType(MediaType.APPLICATION_JSON);
  RequestEntity<byte[]> requestEntity=new RequestEntity<byte[]>(json,httpHeaders,HttpMethod.POST,URI.create(this.url));
  this.restTemplate.exchange(requestEntity,String.class);
}
