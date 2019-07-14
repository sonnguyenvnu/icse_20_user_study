private boolean isOk(HttpResponse response){
  return response != null && response.getStatusCode() < 300;
}
