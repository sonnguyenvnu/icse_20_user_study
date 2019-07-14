private void send100Continue(ChannelHandlerContext ctx){
  HttpResponse response=new DefaultHttpResponse(HTTP_1_1,CONTINUE);
  ctx.writeAndFlush(response);
}
