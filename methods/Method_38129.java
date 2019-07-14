/** 
 * Stores result set.
 */
protected void saveResultSet(final ResultSet rs){
  if (resultSets == null) {
    resultSets=new HashSet<>();
  }
  resultSets.add(rs);
}
