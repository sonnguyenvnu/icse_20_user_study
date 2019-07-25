@Override public void monitor(Map<String,RunStateRecordedFutureTask<ConnectionPool>> connPools){
  try {
    if (connPools == null || connPools.size() == 0) {
      return;
    }
    for (    Map.Entry<String,RunStateRecordedFutureTask<ConnectionPool>> entry : connPools.entrySet()) {
      String poolKey=entry.getKey();
      ConnectionPool pool=FutureTaskUtil.getFutureTaskResult(entry.getValue(),logger);
      List<Connection> serviceOnConnections=new ArrayList<Connection>();
      List<Connection> serviceOffConnections=new ArrayList<Connection>();
      for (      Connection connection : pool.getAll()) {
        if (isConnectionOn(connection)) {
          serviceOnConnections.add(connection);
        }
 else {
          serviceOffConnections.add(connection);
        }
      }
      if (serviceOnConnections.size() > connectionThreshold) {
        Connection freshSelectConnect=serviceOnConnections.get(random.nextInt(serviceOnConnections.size()));
        freshSelectConnect.setAttribute(Configs.CONN_SERVICE_STATUS,Configs.CONN_SERVICE_STATUS_OFF);
        serviceOffConnections.add(freshSelectConnect);
      }
 else {
        if (logger.isInfoEnabled()) {
          logger.info("serviceOnConnections({}) size[{}], CONNECTION_THRESHOLD[{}].",poolKey,serviceOnConnections.size(),connectionThreshold);
        }
      }
      for (      Connection offConn : serviceOffConnections) {
        if (offConn.isInvokeFutureMapFinish()) {
          if (offConn.isFine()) {
            offConn.close();
          }
        }
 else {
          if (logger.isInfoEnabled()) {
            logger.info("Address={} won't close at this schedule turn",RemotingUtil.parseRemoteAddress(offConn.getChannel()));
          }
        }
      }
    }
  }
 catch (  Exception e) {
    logger.error("ScheduledDisconnectStrategy monitor error",e);
  }
}
