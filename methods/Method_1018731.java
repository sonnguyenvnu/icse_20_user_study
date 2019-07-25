void log(SQLServerConnection con){
  if (con.getConnectionLogger().isLoggable(Level.FINE))   con.getConnectionLogger().fine(con.toString() + " Failover server :" + failoverPartner + " Failover partner is primary : " + useFailoverPartner);
}
