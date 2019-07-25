private void redirect(ChannelHandlerContext ctx,AccessLogWriter accessLogWriter,DecodedHttpRequest req,PathAndQuery pathAndQuery,String location){
  respond(ctx,newEarlyRespondingRequestContext(ctx,req,pathAndQuery.path(),pathAndQuery.query()),accessLogWriter,ResponseHeaders.builder(HttpStatus.TEMPORARY_REDIRECT).add(HttpHeaderNames.LOCATION,location),null,null);
}
