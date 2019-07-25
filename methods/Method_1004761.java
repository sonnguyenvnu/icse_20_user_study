private boolean exists(String indexOrType){
  Request req=new SimpleRequest(HEAD,null,indexOrType);
  Response res=executeNotFoundAllowed(req);
  return (res.status() == HttpStatus.OK ? true : false);
}
