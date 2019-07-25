private void respond(ChannelHandlerContext ctx,AccessLogWriter accessLogWriter,DecodedHttpRequest req,PathAndQuery pathAndQuery,HttpStatus status,@Nullable HttpData resContent,@Nullable Throwable cause){
  respond(ctx,newEarlyRespondingRequestContext(ctx,req,pathAndQuery.path(),pathAndQuery.query()),accessLogWriter,status,resContent,cause);
}
