/** 
 * <p> Select the job to which the trigger is associated. Allow option to load actual job class or not. When case of remove, we do not need to load the class, which in many cases, it's no longer exists. </p>
 * @param conn the DB Connection
 * @return the <code>{@link org.quartz.JobDetail}</code> object associated with the given trigger
 * @throws SQLException
 * @throws ClassNotFoundException
 */
public JobDetail selectJobForTrigger(Connection conn,ClassLoadHelper loadHelper,TriggerKey triggerKey,boolean loadJobClass) throws ClassNotFoundException, SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    ps=conn.prepareStatement(rtp(SELECT_JOB_FOR_TRIGGER));
    ps.setString(1,triggerKey.getName());
    ps.setString(2,triggerKey.getGroup());
    rs=ps.executeQuery();
    if (rs.next()) {
      JobDetailImpl job=new JobDetailImpl();
      job.setName(rs.getString(1));
      job.setGroup(rs.getString(2));
      job.setDurability(getBoolean(rs,3));
      if (loadJobClass)       job.setJobClass(loadHelper.loadClass(rs.getString(4),Job.class));
      job.setRequestsRecovery(getBoolean(rs,5));
      return job;
    }
 else {
      if (logger.isDebugEnabled()) {
        logger.debug("No job for trigger '" + triggerKey + "'.");
      }
      return null;
    }
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
