@Override public void block(ProduceMessage message){
  routerSelector.setRouteKey(message.getRouteKey(),platform.getDataSource());
  try {
    platform.update(sqlStatementProvider.getBlockSql(),new Timestamp(System.currentTimeMillis()),message.getSequence());
  }
  finally {
    routerSelector.clearRoute(message.getRouteKey(),platform.getDataSource());
  }
}
