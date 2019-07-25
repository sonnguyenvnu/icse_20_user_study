protected void initialize(ChannelHandlerContext ctx){
switch (state) {
case 1:
case 2:
    return;
}
state=1;
parseIpPort(ctx.channel());
nextCheck=ctx.executor().schedule(new ReportingTask(ctx),reportIntervalMillis,TimeUnit.MILLISECONDS);
}
