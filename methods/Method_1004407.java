@Override public void finish(ProduceMessage message){
  routerSelector.setRouteKey(message.getRouteKey(),platform.getDataSource());
  try {
    platform.update(sqlStatementProvider.getDeleteSql(),message.getSequence());
  }
  finally {
    routerSelector.clearRoute(message.getRouteKey(),platform.getDataSource());
  }
}
