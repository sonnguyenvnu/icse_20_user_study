private Request getModifyRequest(Request request){
  String xid=RootContext.getXID();
  if (StringUtils.isEmpty(xid)) {
    return request;
  }
  Map<String,Collection<String>> headers=new HashMap<>(MAP_SIZE);
  headers.putAll(request.headers());
  List<String> fescarXid=new ArrayList<>();
  fescarXid.add(xid);
  headers.put(RootContext.KEY_XID,fescarXid);
  return Request.create(request.method(),request.url(),headers,request.body(),request.charset());
}
