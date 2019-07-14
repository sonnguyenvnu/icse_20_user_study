/** 
 * <p> Select a calendar. </p>
 * @param conn the DB Connection
 * @param calendarName the name of the calendar
 * @return the Calendar
 * @throws ClassNotFoundException if a class found during deserialization cannot be found be found
 * @throws IOException if there were problems deserializing the calendar
 */
public Calendar selectCalendar(Connection conn,String calendarName) throws ClassNotFoundException, IOException, SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    String selCal=rtp(SELECT_CALENDAR);
    ps=conn.prepareStatement(selCal);
    ps.setString(1,calendarName);
    rs=ps.executeQuery();
    Calendar cal=null;
    if (rs.next()) {
      cal=(Calendar)getObjectFromBlob(rs,COL_CALENDAR);
    }
    if (null == cal) {
      logger.warn("Couldn't find calendar with name '" + calendarName + "'.");
    }
    return cal;
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
