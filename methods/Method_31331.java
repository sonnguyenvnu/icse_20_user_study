/** 
 * List the names of the objects of this type in this schema.
 * @param objectType  The type of objects to list (Sequence, constant, ...)
 * @param querySuffix Suffix to append to the query to find the objects to list.
 * @return The names of the objects.
 * @throws java.sql.SQLException when the object names could not be listed.
 */
private List<String> listObjectNames(String objectType,String querySuffix) throws SQLException {
  String query="SELECT " + objectType + "_NAME FROM INFORMATION_SCHEMA." + objectType + "S WHERE " + objectType + "_SCHEMA = ?";
  if (StringUtils.hasLength(querySuffix)) {
    query+=" AND " + querySuffix;
  }
  return jdbcTemplate.queryForStringList(query,name);
}
