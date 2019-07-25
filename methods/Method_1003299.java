/** 
 * Execute a SQL statement using the given parameters. Prepared statements are kept in a hash map to avoid re-creating them.
 * @param sql the SQL statement
 * @param params the parameters or null
 * @param reusePrepared if the prepared statement can be re-used immediately
 * @return the prepared statement, or null if it is re-used
 */
public PreparedStatement execute(String sql,ArrayList<Value> params,boolean reusePrepared){
  if (conn == null) {
    throw connectException;
  }
  for (int retry=0; ; retry++) {
    try {
synchronized (conn) {
        PreparedStatement prep=preparedMap.remove(sql);
        if (prep == null) {
          prep=conn.getConnection().prepareStatement(sql);
        }
        if (trace.isDebugEnabled()) {
          StringBuilder builder=new StringBuilder(getName()).append(":\n").append(sql);
          if (params != null && !params.isEmpty()) {
            builder.append(" {");
            for (int i=0, l=params.size(); i < l; ) {
              Value v=params.get(i);
              if (i > 0) {
                builder.append(", ");
              }
              builder.append(++i).append(": ");
              v.getSQL(builder);
            }
            builder.append('}');
          }
          builder.append(';');
          trace.debug(builder.toString());
        }
        if (params != null) {
          for (int i=0, size=params.size(); i < size; i++) {
            Value v=params.get(i);
            v.set(prep,i + 1);
          }
        }
        prep.execute();
        if (reusePrepared) {
          reusePreparedStatement(prep,sql);
          return null;
        }
        return prep;
      }
    }
 catch (    SQLException e) {
      if (retry >= MAX_RETRY) {
        throw DbException.convert(e);
      }
      conn.close(true);
      connect();
    }
  }
}
