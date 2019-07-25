private String stringify(Request request){
  byte[] body=request.body() != null ? request.body() : new byte[0];
  String bodyLog=" (binary " + body.length + "-byte body omitted)";
  return "method: " + request.method() + ", " + "uri: " + request.url() + ", " + "headers: " + request.headers() + ", " + bodyLog;
}
