/** 
 * <p> Update the job data map for the given job. </p>
 * @param conn the DB Connection
 * @param job the job to update
 * @return the number of rows updated
 */
public int updateJobData(Connection conn,JobDetail job) throws IOException, SQLException {
  ByteArrayOutputStream baos=serializeJobData(job.getJobDataMap());
  PreparedStatement ps=null;
  try {
    ps=conn.prepareStatement(rtp(UPDATE_JOB_DATA));
    setBytes(ps,1,baos);
    ps.setString(2,job.getKey().getName());
    ps.setString(3,job.getKey().getGroup());
    return ps.executeUpdate();
  }
  finally {
    closeStatement(ps);
  }
}
