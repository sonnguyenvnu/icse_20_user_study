/** 
 * <p> Select the next time that a trigger will be fired. </p>
 * @param conn the DB Connection
 * @return the next fire time, or 0 if no trigger will be fired
 * @deprecated Does not account for misfires.
 */
public long selectNextFireTime(Connection conn) throws SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    ps=conn.prepareStatement(rtp(SELECT_NEXT_FIRE_TIME));
    ps.setString(1,STATE_WAITING);
    rs=ps.executeQuery();
    if (rs.next()) {
      return rs.getLong(ALIAS_COL_NEXT_FIRE_TIME);
    }
 else {
      return 0l;
    }
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
