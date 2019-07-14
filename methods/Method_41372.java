/** 
 * <p> Select the total number of calendars stored. </p>
 * @param conn the DB Connection
 * @return the total number of calendars stored
 */
public int selectNumCalendars(Connection conn) throws SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    int count=0;
    ps=conn.prepareStatement(rtp(SELECT_NUM_CALENDARS));
    rs=ps.executeQuery();
    if (rs.next()) {
      count=rs.getInt(1);
    }
    return count;
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
