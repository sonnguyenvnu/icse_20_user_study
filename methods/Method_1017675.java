private void logging(RoutingContext ctx){
  logger.info(String.format("recommendation request from %s: %d",HOSTNAME,count));
  ctx.next();
}
