/** 
 * List the names of the objects of this type in this schema.
 * @param objectType  The type of objects to list (Sequence, constant, ...)
 * @param querySuffix Suffix to append to the query to find the objects to list.
 * @return The names of the objects.
 * @throws SQLException when the object names could not be listed.
 */
private List<String> listObjectNames(String objectType,String querySuffix) throws SQLException {
  String query="SELECT " + objectType + "name FROM sys.sys" + objectType + "s WHERE schemaid in (SELECT schemaid FROM sys.sysschemas where schemaname = ?)";
  if (StringUtils.hasLength(querySuffix)) {
    query+=" AND " + querySuffix;
  }
  return jdbcTemplate.queryForStringList(query,name);
}
