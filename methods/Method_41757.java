@Override public int insertJobDetail(Connection conn,JobDetail job) throws IOException, SQLException {
  ByteArrayOutputStream baos=serializeJobData(job.getJobDataMap());
  byte[] data=baos.toByteArray();
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    ps=conn.prepareStatement(rtp(INSERT_ORACLE_JOB_DETAIL));
    ps.setString(1,job.getKey().getName());
    ps.setString(2,job.getKey().getGroup());
    ps.setString(3,job.getDescription());
    ps.setString(4,job.getJobClass().getName());
    setBoolean(ps,5,job.isDurable());
    setBoolean(ps,6,job.isConcurrentExectionDisallowed());
    setBoolean(ps,7,job.isPersistJobDataAfterExecution());
    setBoolean(ps,8,job.requestsRecovery());
    ps.executeUpdate();
    ps.close();
    ps=conn.prepareStatement(rtp(SELECT_ORACLE_JOB_DETAIL_BLOB));
    ps.setString(1,job.getKey().getName());
    ps.setString(2,job.getKey().getGroup());
    rs=ps.executeQuery();
    int res=0;
    Blob dbBlob=null;
    if (rs.next()) {
      dbBlob=writeDataToBlob(rs,1,data);
    }
 else {
      return res;
    }
    rs.close();
    ps.close();
    ps=conn.prepareStatement(rtp(UPDATE_ORACLE_JOB_DETAIL_BLOB));
    ps.setBlob(1,dbBlob);
    ps.setString(2,job.getKey().getName());
    ps.setString(3,job.getKey().getGroup());
    res=ps.executeUpdate();
    return res;
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
