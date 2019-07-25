/** 
 * DataStream ???? run() ????????
 * @param ctx
 * @throws Exception
 */
@Override public void run(SourceContext<Student> ctx) throws Exception {
  ResultSet resultSet=ps.executeQuery();
  while (resultSet.next()) {
    Student student=new Student(resultSet.getInt("id"),resultSet.getString("name").trim(),resultSet.getString("password").trim(),resultSet.getInt("age"));
    ctx.collect(student);
  }
}
