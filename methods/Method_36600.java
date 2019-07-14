@Override public List<NewsDO> query(String author) throws SQLException {
  Assert.isTrue(!StringUtils.isEmpty(author));
  Connection connection=dataSource.getConnection();
  Statement statement=connection.createStatement();
  ResultSet resultSet=statement.executeQuery(String.format("SELECT * FROM NewsTable WHERE AUTHOR='%s';",author));
  List<NewsDO> answer=new LinkedList<>();
  while (resultSet.next()) {
    NewsDO newDO=new NewsDO();
    newDO.setAuthor(resultSet.getString(2));
    newDO.setTitle(resultSet.getString(3));
    answer.add(newDO);
  }
  return answer;
}
