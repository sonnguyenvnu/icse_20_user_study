/** 
 * <p> Insert the job detail record. </p>
 * @param conn the DB Connection
 * @param newState the new state for the triggers
 * @param oldState1 the first old state to update
 * @param oldState2 the second old state to update
 * @return number of rows updated
 */
public int updateTriggerStatesFromOtherStates(Connection conn,String newState,String oldState1,String oldState2) throws SQLException {
  PreparedStatement ps=null;
  try {
    ps=conn.prepareStatement(rtp(UPDATE_TRIGGER_STATES_FROM_OTHER_STATES));
    ps.setString(1,newState);
    ps.setString(2,oldState1);
    ps.setString(3,oldState2);
    return ps.executeUpdate();
  }
  finally {
    closeStatement(ps);
  }
}
