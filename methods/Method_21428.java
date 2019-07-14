public String toString(){
  StringBuilder buf=new StringBuilder();
  buf.append("{");
  buf.append("\n\tCreateTime:\"");
  buf.append(Utils.toString(getCreatedTime()));
  buf.append("\"");
  buf.append(",\n\tActiveCount:");
  buf.append(getActiveCount());
  buf.append(",\n\tPoolingCount:");
  buf.append(getPoolingCount());
  buf.append(",\n\tCreateCount:");
  buf.append(getCreateCount());
  buf.append(",\n\tDestroyCount:");
  buf.append(getDestroyCount());
  buf.append(",\n\tCloseCount:");
  buf.append(getCloseCount());
  buf.append(",\n\tConnectCount:");
  buf.append(getConnectCount());
  buf.append(",\n\tConnections:[");
  for (int i=0; i < poolingCount; ++i) {
    DruidConnectionHolder conn=connections[i];
    if (conn != null) {
      if (i != 0) {
        buf.append(",");
      }
      buf.append("\n\t\t");
      buf.append(conn.toString());
    }
  }
  buf.append("\n\t]");
  buf.append("\n}");
  if (this.isPoolPreparedStatements()) {
    buf.append("\n\n[");
    for (int i=0; i < poolingCount; ++i) {
      DruidConnectionHolder conn=connections[i];
      if (conn != null) {
        if (i != 0) {
          buf.append(",");
        }
        buf.append("\n\t{\n\tID:");
        buf.append(System.identityHashCode(conn.getConnection()));
        PreparedStatementPool pool=conn.getStatementPool();
        buf.append(", \n\tpoolStatements:[");
        int entryIndex=0;
        try {
          for (          Map.Entry<DruidPooledPreparedStatement.PreparedStatementKey,PreparedStatementHolder> entry : pool.getMap().entrySet()) {
            if (entryIndex != 0) {
              buf.append(",");
            }
            buf.append("\n\t\t{hitCount:");
            buf.append(entry.getValue().getHitCount());
            buf.append(",sql:\"");
            buf.append(entry.getKey().getSql());
            buf.append("\"");
            buf.append("\t}");
            entryIndex++;
          }
        }
 catch (        ConcurrentModificationException e) {
        }
        buf.append("\n\t\t]");
        buf.append("\n\t}");
      }
    }
    buf.append("\n]");
  }
  return buf.toString();
}
