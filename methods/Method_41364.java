/** 
 * <p> Select a trigger's JobDataMap. </p>
 * @param conn the DB Connection
 * @param triggerName the name of the trigger
 * @param groupName the group containing the trigger
 * @return the <code>{@link org.quartz.JobDataMap}</code> of the Trigger, never null, but possibly empty.
 */
public JobDataMap selectTriggerJobDataMap(Connection conn,String triggerName,String groupName) throws SQLException, ClassNotFoundException, IOException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    ps=conn.prepareStatement(rtp(SELECT_TRIGGER_DATA));
    ps.setString(1,triggerName);
    ps.setString(2,groupName);
    rs=ps.executeQuery();
    if (rs.next()) {
      Map<?,?> map=null;
      if (canUseProperties()) {
        map=getMapFromProperties(rs);
      }
 else {
        map=(Map<?,?>)getObjectFromBlob(rs,COL_JOB_DATAMAP);
      }
      rs.close();
      ps.close();
      if (null != map) {
        return new JobDataMap(map);
      }
    }
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
  return new JobDataMap();
}
