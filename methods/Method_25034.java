protected Response getForbiddenResponse(String s){
  return Response.newFixedLengthResponse(Status.FORBIDDEN,NanoHTTPD.MIME_PLAINTEXT,"FORBIDDEN: " + s);
}
