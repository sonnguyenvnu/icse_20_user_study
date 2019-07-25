@Override public Connection select(List<Connection> connections){
  try {
    if (connections == null) {
      return null;
    }
    int size=connections.size();
    if (size == 0) {
      return null;
    }
    Connection result;
    if (null != this.globalSwitch && this.globalSwitch.isOn(GlobalSwitch.CONN_MONITOR_SWITCH)) {
      List<Connection> serviceStatusOnConnections=new ArrayList<Connection>();
      for (      Connection conn : connections) {
        String serviceStatus=(String)conn.getAttribute(Configs.CONN_SERVICE_STATUS);
        if (!StringUtils.equals(serviceStatus,Configs.CONN_SERVICE_STATUS_OFF)) {
          serviceStatusOnConnections.add(conn);
        }
      }
      if (serviceStatusOnConnections.size() == 0) {
        throw new Exception("No available connection when select in RandomSelectStrategy.");
      }
      result=randomGet(serviceStatusOnConnections);
    }
 else {
      result=randomGet(connections);
    }
    return result;
  }
 catch (  Throwable e) {
    logger.error("Choose connection failed using RandomSelectStrategy!",e);
    return null;
  }
}
