private HttpHeaders createHttpHeaders(){
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
  return HttpHeaders.readOnlyHttpHeaders(headers);
}
