private <R>R handle(ResponseEntity<R> resp){
  if (resp.getStatusCode().is2xxSuccessful()) {
    return resp.getBody();
  }
 else {
    throw new WebResponseException(ResponseEntity.status(resp.getStatusCode()).headers(resp.getHeaders()).build());
  }
}
