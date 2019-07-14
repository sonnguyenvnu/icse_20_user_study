/** 
 * <p> Select all of the jobs contained in a given group. </p>
 * @param conn the DB Connection
 * @param matcher the groupMatcher to evaluate the jobs against
 * @return an array of <code>String</code> job names
 */
public Set<JobKey> selectJobsInGroup(Connection conn,GroupMatcher<JobKey> matcher) throws SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    if (isMatcherEquals(matcher)) {
      ps=conn.prepareStatement(rtp(SELECT_JOBS_IN_GROUP));
      ps.setString(1,toSqlEqualsClause(matcher));
    }
 else {
      ps=conn.prepareStatement(rtp(SELECT_JOBS_IN_GROUP_LIKE));
      ps.setString(1,toSqlLikeClause(matcher));
    }
    rs=ps.executeQuery();
    LinkedList<JobKey> list=new LinkedList<JobKey>();
    while (rs.next()) {
      list.add(jobKey(rs.getString(1),rs.getString(2)));
    }
    return new HashSet<JobKey>(list);
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
