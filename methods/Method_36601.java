@Override public void delete(String author) throws SQLException {
  Assert.isTrue(!StringUtils.isEmpty(author));
  Connection connection=dataSource.getConnection();
  Statement statement=connection.createStatement();
  statement.execute(String.format("DELETE FROM NewsTable WHERE AUTHOR='%s';",author));
}
