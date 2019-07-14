/** 
 * <p> Check whether or not a calendar is referenced by any triggers. </p>
 * @param conn the DB Connection
 * @param calendarName the name of the calendar
 * @return true if any triggers reference the calendar, false otherwise
 */
public boolean calendarIsReferenced(Connection conn,String calendarName) throws SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    ps=conn.prepareStatement(rtp(SELECT_REFERENCED_CALENDAR));
    ps.setString(1,calendarName);
    rs=ps.executeQuery();
    if (rs.next()) {
      return true;
    }
 else {
      return false;
    }
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
