protected Response getInternalErrorResponse(String s){
  return Response.newFixedLengthResponse(Status.INTERNAL_ERROR,NanoHTTPD.MIME_PLAINTEXT,"INTERNAL ERROR: " + s);
}
