/** 
 * <p> Select the distinct instance names of all fired-trigger records. </p> <p> This is useful when trying to identify orphaned fired triggers (a  fired trigger without a scheduler state record.)  </p>
 * @return a Set of String objects.
 */
public Set<String> selectFiredTriggerInstanceNames(Connection conn) throws SQLException {
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    Set<String> instanceNames=new HashSet<String>();
    ps=conn.prepareStatement(rtp(SELECT_FIRED_TRIGGER_INSTANCE_NAMES));
    rs=ps.executeQuery();
    while (rs.next()) {
      instanceNames.add(rs.getString(COL_INSTANCE_NAME));
    }
    return instanceNames;
  }
  finally {
    closeResultSet(rs);
    closeStatement(ps);
  }
}
