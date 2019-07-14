/** 
 * <p> Select the next trigger which will fire to fire between the two given timestamps  in ascending order of fire time, and then descending by priority. </p>
 * @param conn the DB Connection
 * @param noLaterThan highest value of <code>getNextFireTime()</code> of the triggers (exclusive)
 * @param noEarlierThan highest value of <code>getNextFireTime()</code> of the triggers (inclusive)
 * @return A (never null, possibly empty) list of the identifiers (Key objects) of the next triggers to be fired.
 * @deprecated - This remained for compatibility reason. Use {@link #selectTriggerToAcquire(Connection,long,long,int)} instead. 
 */
public List<TriggerKey> selectTriggerToAcquire(Connection conn,long noLaterThan,long noEarlierThan) throws SQLException {
  return selectTriggerToAcquire(conn,noLaterThan,noEarlierThan,1);
}
