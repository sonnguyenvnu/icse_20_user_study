/** 
 * <p> Update the states of all triggers associated with the given job. </p>
 * @param conn the DB Connection
 * @param state the new state for the triggers
 * @return the number of rows updated
 */
public int updateTriggerStatesForJob(Connection conn,JobKey jobKey,String state) throws SQLException {
  PreparedStatement ps=null;
  try {
    ps=conn.prepareStatement(rtp(UPDATE_JOB_TRIGGER_STATES));
    ps.setString(1,state);
    ps.setString(2,jobKey.getName());
    ps.setString(3,jobKey.getGroup());
    return ps.executeUpdate();
  }
  finally {
    closeStatement(ps);
  }
}
