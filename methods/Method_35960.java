@SuppressWarnings("unchecked") public static Collection<Request.Part> parse(byte[] body,String contentType){
  MultiPartInputStreamParser parser=new MultiPartInputStreamParser(new ByteArrayInputStream(body),contentType,null,null);
  try {
    return from(parser.getParts()).transform(new Function<Part,Request.Part>(){
      @Override public Request.Part apply(      Part input){
        return WireMockHttpServletMultipartAdapter.from(input);
      }
    }
).toList();
  }
 catch (  Exception e) {
    return throwUnchecked(e,Collection.class);
  }
}
