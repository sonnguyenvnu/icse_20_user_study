/** 
 * <p> Select the next trigger which will fire to fire between the two given timestamps  in ascending order of fire time, and then descending by priority. </p>
 * @param conn the DB Connection
 * @param noLaterThan highest value of <code>getNextFireTime()</code> of the triggers (exclusive)
 * @param noEarlierThan highest value of <code>getNextFireTime()</code> of the triggers (inclusive)
 * @param maxCount maximum number of trigger keys allow to acquired in the returning list.
 * @return A (never null, possibly empty) list of the identifiers (Key objects) of the next triggers to be fired.
 */
public List<TriggerKey> selectTriggerToAcquire(Connection conn,long noLaterThan,long noEarlierThan,int maxCount) throws SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  List<TriggerKey> nextTriggers=new LinkedList<TriggerKey>();
  try {
    ps=conn.prepareStatement(rtp(SELECT_NEXT_TRIGGER_TO_ACQUIRE));
    if (maxCount < 1)     maxCount=1;
    ps.setMaxRows(maxCount);
    ps.setFetchSize(maxCount);
    ps.setString(1,STATE_WAITING);
    ps.setBigDecimal(2,new BigDecimal(String.valueOf(noLaterThan)));
    ps.setBigDecimal(3,new BigDecimal(String.valueOf(noEarlierThan)));
    rs=ps.executeQuery();
    while (rs.next() && nextTriggers.size() <= maxCount) {
      nextTriggers.add(triggerKey(rs.getString(COL_TRIGGER_NAME),rs.getString(COL_TRIGGER_GROUP)));
    }
    return nextTriggers;
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
