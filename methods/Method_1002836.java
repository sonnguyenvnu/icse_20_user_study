private void respond(ChannelHandlerContext ctx,AccessLogWriter accessLogWriter,DecodedHttpRequest req,HttpStatus status,@Nullable HttpData resContent,@Nullable Throwable cause){
  respond(ctx,newEarlyRespondingRequestContext(ctx,req,req.path(),null),accessLogWriter,status,resContent,cause);
}
