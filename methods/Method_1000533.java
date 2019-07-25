public Sender create(Request request){
  if (request.isGet() || request.isDelete())   return new GetSender(request);
  if ((request.isPost() || request.isPut()) && request.getParams() != null) {
    for (    Object val : request.getParams().values()) {
      if (val instanceof File || val instanceof File[]) {
        return new FilePostSender(request);
      }
    }
  }
  return new PostSender(request);
}
