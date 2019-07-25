@GetMapping("/datasource") public Map<String,Object> datasource() throws SQLException {
  Map result=new HashMap();
  result.put("?????",dataSource.getClass() + "");
  Connection connection=dataSource.getConnection();
  result.put("????????",connection != null);
  connection.close();
  return result;
}
